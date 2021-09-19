package ejemplo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread 
{
	BufferedReader fentrada;
	PrintWriter fsalida;
	static Socket socket = null;

	// Constructor
	public HiloServidor(Socket s) throws IOException 
	{
		socket=s;
		fsalida= new PrintWriter (socket.getOutputStream(),true);
		fentrada= new BufferedReader (new InputStreamReader (socket.getInputStream())); 
	}
	
	public void run() 
	{
		String cadena="";
		while (!cadena.trim().equals("*")) 
		{
			System.out.println("Comunico con: "+ socket.toString());

			try 
			{
				cadena = fentrada.readLine();
			} 
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			}
			fsalida.println(cadena.trim().toUpperCase()); // Enviar mayúsculas

		}// Fin while
		System.out.println("Fin Con:" + socket.toString());
		fsalida.close();

		try 
		{
			fentrada.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			socket.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}