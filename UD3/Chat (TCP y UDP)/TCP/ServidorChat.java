package tcp;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServidorChat extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	static ServerSocket servidor;
	static final int PUERTO=44444; // Puerto en el que se escucha
	static int CONEXIONES = 0; // Cuenta el número de conexiones
	static int ACTUALES = 0;
	static int MAXIMO = 10;
	static JTextField mensaje= new JTextField("");
	static JTextField mensaje2= new JTextField("");
	private JScrollPane scrollpanel;
	static JTextArea textarea;
	JButton salir = new JButton("Salir");
	static Socket table[]=new Socket[10]; /// Almacena los sockets de los clientes
	
	public static void main(String[] args) throws IOException 
	{
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado...");
		ServidorChat pantalla = new ServidorChat();
		pantalla.setBounds(0,0,540,400);
		pantalla.setVisible(true);
		mensaje.setText("NUMERO DE CONEXIONES ACTUALES");
			
		///Se admiten hasta 10 Conexiones
		while (CONEXIONES < MAXIMO) 
		{
			Socket s = new Socket();
			s=servidor.accept(); /// Esperando al cliente
			table[CONEXIONES]=s; //Guardamos el socket
			CONEXIONES ++;
			ACTUALES ++;
			HiloServidor hilo=new HiloServidor(s);
			hilo.start();	
		}
			
		// Cuando finaliza bucle cerrar servidor si no se ha cerrado antes
		if (!servidor.isClosed()) 
		{
			mensaje2.setForeground(Color.red);
			mensaje2.setText("Máximo número de conexiones establecidas" + CONEXIONES);
				
			servidor.close();
		}
			
		System.out.println("Servidor finalizado...");	
	}
	
	/**
	 * Create the application.
	 */
	public ServidorChat() 
	{
		super ("VENTANA DEL SERVIDOR DE CHAT");
		getContentPane().setLayout(null);
		mensaje.setBounds (10,10, 400,30);	
		getContentPane().add(mensaje);
		mensaje.setEditable(false);
		mensaje2.setBounds(10,348,400,30);		
		getContentPane().add(mensaje2);
		mensaje2.setEditable(false);
		
		textarea = new JTextArea();
		scrollpanel = new JScrollPane(textarea);
		scrollpanel.setBounds(10,50,500,300);   
		getContentPane().add(scrollpanel);
		salir.setBounds(420,10, 100,30);	
		getContentPane().add(salir);
		
		textarea.setEditable(false);
		salir.addActionListener(this);
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
	}

	/////// Cuando pulsamos el botón click
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == salir)
			try
			{
				servidor.close();
				System.exit(0);
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}