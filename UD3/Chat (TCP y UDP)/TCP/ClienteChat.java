package tcp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteChat extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	Socket socket = null;
	// Streams
	DataInputStream fentrada; // para leer mensajes de todos
	DataOutputStream fsalida; // para escribir sus mensajes
	String nombre;
	static JTextField mensaje=new JTextField();
	private JScrollPane scrollpane1;
	static JTextArea textarea1;
	JButton boton = new JButton ("Enviar");
	JButton desconectar = new JButton ("Salir");
	boolean repetir = true;
		
	public static void main(String[] args) 
	{
		int puerto=44444;
		String nombre = JOptionPane.showInputDialog("Introduce tu nombre o Nick");
			
		Socket s= null;
		// Cliente y servidor se ejecutan en la máquina local
		try 
		{
			s=new Socket ("localhost", puerto);
		} 
		catch (UnknownHostException e) 
		{
			JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),"<<MENSAJE DE ERROR: 1>>",JOptionPane.ERROR_MESSAGE);;
			System.exit(0);	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if (!nombre.trim().equals("")) 
		{
			ClienteChat cliente = new ClienteChat(s,nombre);
			cliente.setBounds(0,0,540,500);
			cliente.setVisible(true);
			cliente.ejecutar();
		} 
		else
			System.out.println("El nombre está vacío,....");		
	}
	
	public void run() 
	{
		ServidorChat.mensaje.setText("NUMERO DE CONEXIONES ACTUALES: "+ ServidorChat.ACTUALES);
		// Nada más conectarse el cleiente le envío todos los mensajes
		String texto= ServidorChat.textarea.getText();
		EnviarMensajes(texto);	
	}
	
	// ENVIA LOS MENSAJES DEL TEXTAREA A LOS CLIENTES DEL CHAT
	private void EnviarMensajes (String texto) 
	{
		int i;
		for (i = 0; i< ServidorChat.CONEXIONES; i++) 
		{
			Socket s1= ServidorChat.table[i];//  .tabla[i];
			try 
			{
				DataOutputStream fsalida = new DataOutputStream(s1.getOutputStream());
				fsalida.writeUTF(texto);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
	}

	/**
	 * Creamos el constructor
	 */
	public ClienteChat (Socket s, String nombre) 
	{
		super ("CONEXIÓN DEL CLIENTE CHAT: " + nombre);
		setLayout (null);
		
		mensaje.setBounds(10, 10, 400, 30);add(mensaje);
		textarea1 = new JTextArea();
		scrollpane1 = new JScrollPane(textarea1);
		scrollpane1.setBounds(10,50,400,300);add(scrollpane1);
		boton.setBounds(420,10,100,30);add(boton);
		desconectar.setBounds(420,50,100,30);add(desconectar);		
		textarea1.setEditable(false);
		boton.addActionListener(this);
		desconectar.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		socket = s;
		this.nombre=nombre;
		
		try
		{
			fentrada = new DataInputStream(socket.getInputStream());
			fsalida = new DataOutputStream(socket.getOutputStream());
			String texto = " > Entra en el Chat ..." + nombre;
			fsalida.writeUTF(texto); //escribe mensaje de entrada
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	////// Boton click
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == boton) 
		{
			String texto = nombre + "> "+ mensaje.getText();
				
			try 
			{
				mensaje.setText("");
				fsalida.writeUTF(texto);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}	
		}
		if (e.getSource() == desconectar) 
		{
			String texto = " > Abandona el Chat ..."+nombre;
			try 
			{
				fsalida.writeUTF(texto);
				fsalida.writeUTF("*");
				repetir = false; // Para salir del bucle
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}	
		}
	}		
	
	public void ejecutar () 
	{
		String texto = "";
		while (repetir) 
		{
			try 
			{
				texto = fentrada.readUTF();
				textarea1.setText(texto);
			} 
			catch (IOException e) 
			{
				JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),"<<MENSAJE DE ERROR: 2>>",JOptionPane.ERROR_MESSAGE);;
				repetir = false; // Salir del bucle	
			}	
		} 
		try 
		{
			socket.close();
			System.exit(0);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	} 
}