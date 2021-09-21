package actividad5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidor extends Thread{
		Socket socket;
		ObjectOutputStream outObjeto; //stream de salida
		ObjectInputStream inObjeto;   //stream de entrada
		EstructuraFicheros NF;
		
		// Constructor
		public HiloServidor (Socket s, EstructuraFicheros nF) throws IOException {
			socket = s;
			NF = nF;
			inObjeto = new ObjectInputStream (socket.getInputStream());
			outObjeto= new ObjectOutputStream (socket.getOutputStream());
		}
		
		public void run() {
		
			try {
				// Enviar al cliente el objeto EstructuraFicheros
				outObjeto.writeObject(NF);
				while (true) {
					
					System.out.println("EEEEEEEnviado");
					Object peticion;
					
					try {
						peticion = inObjeto.readObject();
						// Comprobar qué quiere el cliente
						if (peticion instanceof PideFichero) {
							// El cliente pide un fichero al servidor
							PideFichero fichero = (PideFichero) peticion;
							EnviaFichero(fichero);
						}
						
						if (peticion instanceof EnviaFichero) {
							// El cliente envía un fichero al servidor para cargarlo
							EnviaFichero fic = (EnviaFichero) peticion;
							File d=new File (fic.getDirectorio());
							File f1=new File (d, fic.getNombre());
							// Creación del fichero en el directorio,
							// con los bytes enviados en el objeto
							FileOutputStream fos = new FileOutputStream(f1);
							fos.write(fic.getContenidoFichero());
							fos.close();
							// Creación de la nueva estructura de directorios
							EstructuraFicheros n = new EstructuraFicheros (fic.getDirectorio());
							outObjeto.writeObject(n);// Se envía al cliente
						}
					
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						inObjeto.close();
						outObjeto.close();
						socket.close();
						System.out.println("Cerrando cliente");			
					}					
				} // while
	
			} catch (IOException e) {
			  // Cuando un cliente cierra la conexión					
			}
		}// Fin run
		
		// Método que envía al cliente el fichero solicitado
		private void EnviaFichero (PideFichero fich) {	
			
			try {
				
				// Obtención del fichero
				File fichero = new File (fich.getNombreFichero());
				FileInputStream filein = null;
				filein= new FileInputStream(fichero);
				long bytes=fichero.length();
				byte[] buff = new byte [(int) bytes];
				int i,j = 0;
				// Lectura del fichero y llenado del array
				try {
					while ((i = filein.read()) != -1 ) { // Lectura de bytes
						buff[j]=(byte) i;
						j++;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					filein.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Object ff=new ObtieneFichero(buff);
				// Envío del objeto ObtieneFichero con los bytes del fichero
				try {
					outObjeto.writeObject(ff);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// EnviaFichero
}