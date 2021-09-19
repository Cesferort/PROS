package ejemplo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{

	public static void main (String[] args) throws IOException 
	{
		int puerto = 6000;
		ServerSocket servidor = new ServerSocket (puerto);
		while (true) 
		{
			Socket cliente = new Socket();
			Socket clienteConectado = servidor.accept();

			System.out.println("HiloServidor Conectado.....");

			HiloServidor hilo = new HiloServidor (cliente);
			hilo.start();
		}
	}
}