import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ejemplo5 
{

	public static void main (String[]args)
	{
		String host = "localhost";
		int puerto = 6000;

		try 
		{
			// Abrimos Socket
			Socket cliente = new Socket (host, puerto);

			InetAddress ip = cliente.getInetAddress();

			System.out.println("Puerto local: " + cliente.getLocalPort());
			System.out.println("Puerto Remoto: " + cliente.getPort());
			System.out.println("Host Remoto: " + ip.getHostName());
			System.out.println("IP Host Remoto: " + ip.getHostAddress().toString());

			cliente.close();
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}