package actividad4;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClienteFTPBasico extends JFrame 
{
	private static final long serialVersionUID=1L;
	
	private JFrame frame;
	static JTextField cab;
	static JTextField cab3;
	static JTextField cab2;
	
	static JList listaDirec;
	
	// Datos del servidor FTP  
	static FTPClient cliente = new FTPClient();
	String servidor = "192.168.0.11";
	String user = "dinux";
	String pasw = "dinux";
	boolean login;
	static String direcInicial = "/";
	
	//Para saber el directorio y fichero seleccionado
	static String direcSelec = direcInicial;
	static String ficheroSelec = "";
	private JTextField campo;
	private JTextField campo2;
	
	// Lanzar la aplicación 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteFTPBasico window = new ClienteFTPBasico();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crear la aplicación
	public ClienteFTPBasico() throws IOException {
	    super ("CLIENTE BASICO FTP");
		initialize();
		
		// Preparar campos de pantalla
		campo.setText("<<ARBOL DE DIRECTORIOS CONSTRUIDO >>");
		cab.setText("Servidor FTP: "+servidor);
		cab2.setText("Usuario: " + user);
	
	}
	
	// Inicializar los contenidos del frame
	private void initialize() throws IOException {
		
		// Mostrar en consola los mensajes devueltos por el servidor
		cliente.addProtocolCommandListener(new PrintCommandListener (new PrintWriter (System.out)));
	  	System.out.println("Hola " + servidor);
		cliente.connect(servidor); // Conexión al servidor
	  	System.out.println("Hola ");
		login = cliente.login(user, pasw);

		// Establecer el directorio de trabajo actual
		cliente.changeWorkingDirectory(direcInicial);
		
		// Obtener ficheros y directorios del directorio actual
		FTPFile[] files = cliente.listFiles();
			
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton botonCargar = new JButton("Suvir Fichero");
		botonCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonCargar.setBounds(632, 155, 143, 29);
		frame.getContentPane().add(botonCargar);
		
		JButton botonDescargar = new JButton("Descargar Fichero");
		botonDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonDescargar.setBounds(632, 186, 143, 23);
		frame.getContentPane().add(botonDescargar);
		
		JButton botonBorrar = new JButton("Eliminar Ficheró");
		botonBorrar.setBounds(632, 207, 143, 29);
		frame.getContentPane().add(botonBorrar);
		
		JButton botonCreaDir = new JButton("Crear Carpeta");
		botonCreaDir.setBounds(632, 232, 143, 29);
		frame.getContentPane().add(botonCreaDir);
		
		JButton botonDelDir = new JButton("Eliminar Carpeta");
		botonDelDir.setBounds(632, 260, 143, 29);
		frame.getContentPane().add(botonDelDir);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(632, 285, 143, 29);
		frame.getContentPane().add(botonSalir);
		
		cab = new JTextField();
		cab.setBounds(28, 6, 363, 28);
		frame.getContentPane().add(cab);
		cab.setColumns(10);
		
		cab3 = new JTextField();
		cab3.setBounds(28, 46, 363, 28);
		frame.getContentPane().add(cab3);
		cab3.setColumns(10);
		
		cab2 = new JTextField();
		cab2.setBounds(403, 6, 256, 28);
		frame.getContentPane().add(cab2);
		cab2.setColumns(10);
		
		cab3.setText("DIRECTORIO RAÍZ: "+ direcInicial);
	
		campo = new JTextField();
		campo.setColumns(10);
		campo.setBounds(28, 271, 397, 28);
		frame.getContentPane().add(campo);
		
		campo2 = new JTextField();
		campo2.setColumns(10);
		campo2.setBounds(28, 299, 397, 28);
		frame.getContentPane().add(campo2);
		
		listaDirec = new JList();
		listaDirec.setBounds(0, 134, 57, -51);
		frame.getContentPane().add(listaDirec);	
		
		JScrollPane barraDesplazamiento = new JScrollPane(listaDirec);
		barraDesplazamiento.setBounds(35, 102, 500, 155);
		frame.getContentPane().add(barraDesplazamiento);
		frame.getContentPane().setLayout(null);
		
		// Construir la lista de ficheros y directorios del diretorio de trabajo actual
		llenarLista(files, direcInicial);

		// Implementar el clic sobre la lista
		listaDirec.addListSelectionListener(new ListSelectionListener() {
		 
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				// TODO Auto-generated method stub
				if (lse.getValueIsAdjusting()) {
					ficheroSelec="";
					
					// Elemento que se ha seleccionado de la lista
					String fic;
					if(listaDirec.getSelectedValue() == null)
						fic = "null        ";
					else
						fic=listaDirec.getSelectedValue().toString();
					if (listaDirec.getSelectedIndex() == 0) {
						// Hacer clic en el primer elemento del JList
						// Comprobar si es el directorio inicial
							try {
								cliente.changeToParentDirectory();
								direcSelec=cliente.printWorkingDirectory();
								FTPFile[] ff2=null;
								// directorio de trabajo actual = directorio padre
								cliente.changeWorkingDirectory(direcSelec);
								// Obtener ficheros y directorios
								ff2 = cliente.listFiles();
								campo.setText("");
								// Llenar la lista con fichero del directorio padre 
								llenarLista(ff2, direcSelec);
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					}
					// No se hace click en el primer elemento del JLIST
					// Puede ser un fichero o un directorio	
					else {
							if (fic.substring(0,6).equals("(DIR) ")) {
								// SE TRATA DE UN DIRECTORIO	
								try {
									fic=fic.substring(6);
									String direcSelec2="";
									if (direcSelec.equals("/"))
											direcSelec2=direcSelec+fic;
									else
											direcSelec2=direcSelec + "/" + fic;
									FTPFile[] ff2=null;
									cliente.changeWorkingDirectory(direcSelec2);
									ff2 = cliente.listFiles();
									campo.setText("DIRECTORIO: "+fic+", "+ ff2.length +" elementos");
									// directorio actual
									direcSelec = direcSelec2;
									// Llenar la lista con los datos del directorio
									llenarLista (ff2, direcSelec);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						
							
							}
							else {
								// SE TRATA DE UN FICHERO
								ficheroSelec = direcSelec;
								if (direcSelec.equals("/"))
									ficheroSelec += fic;
								else
									ficheroSelec += "/"+fic;
								campo.setText("FICHERO seleccionado:" + ficheroSelec );
								ficheroSelec = fic; // Me quedo con el nombre
							}// fin else  fichero
							
							
						}//fin else fichero directorio
					campo2.setText("DIRECTORIO ACTUAL:" + direcSelec);			
					
				} // fin if inicial
				
			}
	    }); //////////// fin click lista

		// Implementar el botón Salir
		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					cliente.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}} );
		
		// Implementar el botón Subir Fichero
		botonCargar.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser f;
				File file;
				f = new JFileChooser();
				//////// Solo se pueden seleccioner ficheros
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				// Titulo de la ventana
				f.setDialogTitle("Selecciona el fichero a SUBIR al serbidor");
				// Mostrar la ventana
				int returnVal = f.showDialog(f,  "Cargar");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// Fichero seleccionado
					file = f.getSelectedFile();
					// Nombre completo del fichero
					String archivo = file.getAbsolutePath();
					// Solo nombre del fichero
					String nombreArchivo = file.getName();
					try {
						SubirFichero(archivo, nombreArchivo);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}	
			}
		});
		
		// Implementar el botón Descargar Fichero
		botonDescargar.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String directorio=direcSelec;
				if (!direcSelec.equals("/"))
					directorio = directorio + "/";
				if (!ficheroSelec.equals("")) {
					try {
						DescargarFichero(directorio + ficheroSelec, ficheroSelec);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		
		//Implementar el botón Eliminar Fichero
		botonBorrar.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String directorio=direcSelec;
				if (!direcSelec.equals("/"))
						directorio = directorio + "/";
				if (!ficheroSelec.equals(""))
						BorrarFichero(directorio+ficheroSelec, ficheroSelec);
				
			}
			
		});
		
		//Implementar el botón Crear Carpeta
		botonCreaDir.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombreCarpeta = JOptionPane.showInputDialog(null, "Introduce el numero del directorio","carpeta");
				if (!(nombreCarpeta == null) ) {
					String directorio=direcSelec;
					if (!direcSelec.equals("/")) directorio = directorio + "/";
						// Nombre del directorio a crear
						directorio +=nombreCarpeta.trim(); 
						
						try {
							if (cliente.makeDirectory(directorio)) {
								String m=nombreCarpeta.trim() + " => Se ha creado korrektamente...";
								JOptionPane.showConfirmDialog(null, m);
								campo.setText(m);
								// Directorio de trabajo actual
								cliente.changeWorkingDirectory(direcSelec);
								FTPFile[] ff2 = null;
								// Obtener ficheros del directorio actual
								ff2 = cliente.listFiles();
								// Llenar la lista
								llenarLista(ff2, direcSelec);
							}
							else
									JOptionPane.showConfirmDialog(null, nombreCarpeta.trim()+" No se ha podido crear");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				}
			}
			
		});
		
		// Implementar el botón Eliminar Carpeta
		botonDelDir.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombreCarpeta = JOptionPane.showInputDialog(null,"Introduce el nombre del directorio a eliminar","carpeta");
				if (!(nombreCarpeta==null)) {
					String directorio=direcSelec;
					if (!direcSelec.equals("/"))
						directorio=directorio+"/";
					// Nombre del directorio a eliminar
					directorio+=nombreCarpeta.trim();
					try {
						if (cliente.removeDirectory(directorio)) {
								String m=nombreCarpeta.trim()+" => Se ha eliminado correctamente....";
								JOptionPane.showMessageDialog(null, m);
								campo.setText(m);
								cliente.changeWorkingDirectory(direcSelec);
								FTPFile[] ff2 = null;
								// Obtener ficheros del directorio actual
								ff2 = cliente.listFiles();
								// Llenar la lista
								llenarLista(ff2, direcSelec);
						}
						else
							JOptionPane.showConfirmDialog(null, nombreCarpeta.trim()+" No se ha podido crear");
							
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}	
		});		
	}
	
	
	// Llenar la lista
	private static void llenarLista(FTPFile[] files, String direc2) {
	
		if (files == null) return;
		// Crear un objeto DefaultListModel
		DefaultListModel modeloLista = new DefaultListModel();
		modeloLista=new DefaultListModel();
		
		// Definir propiedades para la lista, color y tipo de fuente
		listaDirec.setForeground(Color.blue);
		Font fuente=new Font("Courier",Font.PLAIN, 12);
		listaDirec.setFont(fuente);
		
		// Eliminar los elementos de la lista
		listaDirec.removeAll();
		try {
			cliente.changeWorkingDirectory(direc2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Añadir el directorio de trabajo al listmodel, primer elemento
		modeloLista.addElement(direc2);
		
		// Recoger el array con los ficheros y directorios
		for (int i = 0; i < files.length; i++) {
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals(".. ")) {
				// Saltar los directorios . y ..
				// Obtener el nombre del fichero o directorio
				String f = files[i].getName();
						
				// Si es directorio añadir al nombre DIR
				if (files[i].isDirectory()) f = "(DIR) " +f;
						
				// Añadir el nombre del fichero o directorio al listmodel
				modeloLista.addElement(f);
										
			} // fin if
		} // fin for
		
		listaDirec.setModel(modeloLista);
		
	}
	
	////////// Subir Fichero
	private boolean SubirFichero (String archivo, String soloNombre) throws IOException {
		cliente.setFileType(FTP.BINARY_FILE_TYPE);
		BufferedInputStream in = new BufferedInputStream (new FileInputStream(archivo));
		boolean ok=false;
		// Directorio de trabajo actual
		cliente.changeWorkingDirectory(direcSelec);
		if (cliente.storeFile(soloNombre, in)) {
			String s = " " + soloNombre + " =>Subido correctamente";
			campo.setText(s);
			campo2.setText("Se va a actualizar el árbol de directorios....");
			FTPFile[] ff2=null;
			// Obtener ficheros del directorio actual
			ff2=cliente.listFiles();
			// Llenar la lista con los ficheros del directorio actual
			llenarLista(ff2,direcSelec);
			ok=true;
		}
		else {
			campo.setText("No se ha podido subir ...." + soloNombre);
		}
		return ok;
	}
	
	////////// Descargar Fichero
	private void DescargarFichero (String NombreCompleto, String nombreFichero) throws IOException {
		File file;
		String archivoyCarpetaDestino = "";
		String carpetaDestino = "";
		JFileChooser f = new JFileChooser();

		// Solo se pueden seleccionar directorios
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// Titulo de la ventana
		f.setDialogTitle("Selecciona el directorio donde descargar el fichero");
		int returnVal = f.showDialog(null, "Descargar");
		
		if (returnVal == JFileChooser.APPROVE_OPTION){
			// Obtener carpeta de destino OJO!!!!!! 
			// Esto da problemas por lo menos en MAC
			// Con ficheros traducidos no funciona bien
			// Coge el destino: /Users/aitor/Desktop/Escriotorio 
			// Se puede poner el path absoluto para que no de problemas
			// 
			//file = f.getSelectedFile();
			file = f.getCurrentDirectory();

			carpetaDestino = file.getAbsolutePath().toString();

			System.out.println("Nombre completo"+carpetaDestino);
			// Construir el nombre completo que se creará en nuestro disco
			archivoyCarpetaDestino = carpetaDestino + File.separator + nombreFichero;
			
			cliente.setFileType(FTP.BINARY_FILE_TYPE);

			BufferedOutputStream out= new BufferedOutputStream (new FileOutputStream(archivoyCarpetaDestino));
			if (cliente.retrieveFile(NombreCompleto, out)) 
				JOptionPane.showMessageDialog(null, nombreFichero + " => Se ha descaergado correctamente");
				else	
				JOptionPane.showMessageDialog(null, nombreFichero + " => No se ha podido descargar");

			out.close();

		}
	}
	
	////////// Borrar Fichero
	private void BorrarFichero(String NombreCompleto, String nombreFichero) {
		// Pedir confirmación del fichero a borrar
		int seleccion = JOptionPane.showConfirmDialog(null,"¿Desea borrar el fichero seleccionado?");
		if (seleccion == JOptionPane.OK_OPTION) {

			// Directorio de trabajo actual
			try {
				if (cliente.deleteFile(NombreCompleto)){
					String m=nombreFichero+ "=> Eliminado correctamente";
					JOptionPane.showMessageDialog(null, m);
					campo.setText(m);
					cliente.changeWorkingDirectory(direcSelec);
					FTPFile[] ff2 = null;
					// Obtener ficheros del directorio actual
					ff2=cliente.listFiles();
					// Llenar la lista
					llenarLista(ff2,direcSelec);
				}
			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
}