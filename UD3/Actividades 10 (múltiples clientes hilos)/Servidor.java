package actividad10;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{

	public static void main (String[] args) throws IOException 
	{
		int puerto = 6000;
		
		@SuppressWarnings("resource")
		ServerSocket servidor = new ServerSocket(puerto);
		while (true) 
		{
			Socket cliente = servidor.accept();
			System.out.println("Cliente Conectado.....");

			HiloServidor hilo = new HiloServidor(cliente);
			hilo.start();
		}
	}
}