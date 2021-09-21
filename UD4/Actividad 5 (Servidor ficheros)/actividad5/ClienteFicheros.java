package actividad5;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class ClienteFicheros extends JFrame implements Runnable {

	private static final long serialVersionUID=1L;
	
	private JFrame frame;
	static JTextField cab;
	static JTextField cab3;
	static JTextField cab2;
	
	//Para saber el directorio y fichero seleccionados
	private JTextField campo;
	private JTextField campo2;
	
	static Socket socket;
	EstructuraFicheros nodo=null; // objeto EsturcturaFicheros actual
	ObjectInputStream inObjeto;   // stream de entrada
	ObjectOutputStream outObjeto; // stream de salida
	EstructuraFicheros Raiz; // Objeto estructura de ficheros raiz
	
	// Lista de los datos del directorio
	static JList listaDirec=new JList();
	// Para obtener el directorio y fichero seleccionados
	static String direcSelec = ""; // nombre del directorio actual
	static String ficheroSelec = ""; 
	static String ficherocompleto ="";

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		int puerto=  44441;
		Socket s= new Socket ("localhost", puerto);
		ClienteFicheros hiloC= new ClienteFicheros(s);
		
		new Thread(hiloC).start();
			
	}
	@Override
	public void run() {
		initialize();
		try {
			cab.setText("Conectando con el servidor");
			// Obtener directorio ra�z
			Raiz= (EstructuraFicheros) inObjeto.readObject();
			EstructuraFicheros[] nodos = Raiz.getLista(); // Lista de ficheros
			direcSelec = Raiz.getPath();
			llenarLista(nodos, Raiz.getNumeFich());
			cab3.setText("RAIZ:"+direcSelec);
			cab.setText("CONECTADO AL SERVIDOR DE FICHEROS");
			campo2.setText("N�mero de ficheros en el directorio: "+ Raiz.getNumeFich());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		// TODO Auto-generated method stub
		
	}

	/**
	 * Create the application.
	 */
	public ClienteFicheros(Socket s) {
		super ("SERVIDOR DE FICHEROS BASICO");
		socket = s;
		try {
			outObjeto = new ObjectOutputStream (socket.getOutputStream());
			inObjeto = new ObjectInputStream (socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private  void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton botonSubir = new JButton("Subir Fichero");
		/*
		botonSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		})
		*/;
		botonSubir.setBounds(632, 155, 143, 29);
		frame.getContentPane().add(botonSubir);
		
		JButton botonDescargar = new JButton("Descargar Fichero");
		/*
		botonDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		*/
		botonDescargar.setBounds(632, 186, 143, 23);
		frame.getContentPane().add(botonDescargar);
		
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
		frame.setVisible(true);		

		// Implementaci�n de los botones
		
		// Bot�n Salir
	
		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					try {
						socket.close();
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			}
			
		});
		
		listaDirec.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent lse) {
				// TODO Auto-generated method stub
				if (lse.getValueIsAdjusting()) {
					ficheroSelec="";
					ficherocompleto ="";
					// Obtenci�n del elemento seleccionado de la lista
					nodo=(EstructuraFicheros) listaDirec.getSelectedValue();
					if (nodo.isDir()) {
						// Es un diretorio
						campo.setText("FUNCI�N NO IMPLEMENTADA");
						
					} else {
						// Es un fichero
						ficheroSelec=nodo.getName();
						ficherocompleto = nodo.getPath();
						campo.setText("FICHERO seleccionado: " + ficheroSelec);
					} // Fin else
						
				}
			}

		});  // fin lista
		
		// Bot�n Subir
		
		botonSubir.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser f;
				File file;
				f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				f.setDialogTitle("Selecciona el Fichero a SUBIR AL SERVIDOR DE FICHEROS");
				int returnVal = f.showDialog (f, "Cargar");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = f.getSelectedFile();
					String archivo = file.getAbsolutePath();
					String nombreArchivo = file.getName();
					BufferedInputStream in;
					
					
					try {
						in = new BufferedInputStream (new FileInputStream(archivo));
						long bytes = file.length();
						byte[] buff = new byte[(int) bytes];
						int i, j =0;
						// Lectura del fichero y almacenamiento en array de bytes
						try {
							while ((i = in.read()) != -1) {
								buff[j] = (byte) i;  // Carga de datos en el array
								j++;
							}
							in.close(); // Cierre del stream de entrada
							System.out.println("Fichero Completo"+ nombreArchivo);

							// Crear objeto EnviarFichero con los bytes del fichero, 
							// el nombre y el directorio donde se cargar�
							Object ff=new EnviaFichero (buff, nombreArchivo, direcSelec);
							// Env�o al servidor
							outObjeto.writeObject(ff);
							JOptionPane.showConfirmDialog(null, "Fichero SUBIDO");
							// Obtenci�n de la nueva lista de ficheros
							try {
								nodo= (EstructuraFicheros) inObjeto.readObject();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							EstructuraFicheros[] lista = nodo.getLista();
							direcSelec = nodo.getPath();
							llenarLista(lista, nodo.getNumeFich());
							campo2.setText("N�mero de ficheros en el directorio: " + lista.length);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
			
		}); // Fin bot�n Subir
		
		// Bot�n Descarcar
		
		botonDescargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				if (ficherocompleto.equals("")) return; // No se ha seleccionado
				/// Petici�n del fichero completo
				PideFichero pido = new PideFichero(ficherocompleto);
				System.out.println("Fichero Completo"+ ficherocompleto);
				try {
					outObjeto.writeObject(pido);
					// Se crea fichero con el nombre del seleccionado
					// en el directorio actual
					FileOutputStream fos = new FileOutputStream(ficheroSelec);
					// Recepci�n del fichero del servidor
					Object obtengo= inObjeto.readObject();
					if (obtengo instanceof ObtieneFichero) {
						ObtieneFichero fic = (ObtieneFichero) obtengo;
						fos.write(fic.getContenidoFichero()); // escribo bytes
						fos.close();
						JOptionPane.showMessageDialog(null, "FICHERO DESCARGADO");			
					}
					
					
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		}); // Fin bot�n Descargar	
	}
		
	private static void llenarLista (EstructuraFicheros[] files, int numero) {
		if (numero == 0) return;
		DefaultListModel modeloLista = new DefaultListModel();
		modeloLista = new DefaultListModel();
		listaDirec.setForeground(Color.blue);
		Font fuente = new Font ("Courier", Font.PLAIN, 12);
		listaDirec.setFont(fuente);
		listaDirec.removeAll();
		for (int i = 0; i < files.length; i++) {
			modeloLista.addElement(files[i]);
		}	
		listaDirec.setModel(modeloLista);
	}

}
