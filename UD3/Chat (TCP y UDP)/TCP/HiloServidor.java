package tcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidor extends Thread 
{
	DataInputStream  fentrada;
	Socket socket = null;
	
	public HiloServidor (Socket s) 
	{
		socket = s;
		try 
		{
			//ServidorChat.mensaje2.setText("TODO OK");
			fentrada = new DataInputStream (socket.getInputStream());
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("ERROR E/S");
			e.printStackTrace();
		}	
	}
	
	public void run() 
	{
		ServidorChat.mensaje.setText("NUMERO DE CONEXIONES ACTUALES: "+ServidorChat.ACTUALES);
		// Nada más conectarse el cleiente le envío todos los mensajes
		String texto= ServidorChat.textarea.getText();
		EnviarMensajes(texto);
		
		while (true) 
		{
			String cadena="";
			try 
			{
				cadena=fentrada.readUTF(); // Leo lo que el cliente escribe
				if (cadena.trim().equals("*")) 
				{
					ServidorChat.ACTUALES--;
					ServidorChat.mensaje.setText("NUMERO DE CONEXIONES ACTUALES" + ServidorChat.ACTUALES);
					break;// Salir del while
				}
			
			ServidorChat.textarea.append(cadena + "\n");
			texto = ServidorChat.textarea.getText();
			EnviarMensajes(texto); /// Envio texto a todos los clientes
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
	}
}