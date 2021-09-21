import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejemplo4 
{

	public static void main (String[]args)
	{

		int puerto = 6000;

		try 
		{
			ServerSocket servidor = new ServerSocket (puerto);

			System.out.println("Hay un servidor escuchando en el puerto " + servidor.getLocalPort());

			// Realiza la conexión con cliente 1
			Socket cliente1 = servidor.accept();

			// Realiza la conexión con cliente 2
			Socket cliente2 = servidor.accept();

			// Cierra la conexión
			servidor.close();
			
			cliente1.close();
			cliente2.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
	}
}