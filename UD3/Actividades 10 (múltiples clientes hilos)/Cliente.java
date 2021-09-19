package actividad10;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente 
{
	
	public static void main(String[] args) 
	{
		System.out.println("PROGRAMA CLIENTE INICIANDO");
		int puerto = 6000;
		try 
		{
			String texto = "";
			InetAddress ip = InetAddress.getLocalHost();
			
			Socket cliente = new Socket(ip, puerto);
			DataOutputStream dos;
			DataInputStream dis = null;
			while(true)
			{
				System.out.println("Introduce una cadena: ");
				texto = Consola.leeString();
				
				dos = new DataOutputStream(cliente.getOutputStream());
				dos.writeUTF(texto);
				
				if(!texto.equals("*"))
				{
					dis = new DataInputStream(cliente.getInputStream());
					System.out.println(" =>Respuesta: " + dis.readUTF());
				}	
				else
				{
					dos.close();
					dis.close();
					cliente.close();
					break;
				}
			}
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		System.out.println("Fin del envío....");
	}
}