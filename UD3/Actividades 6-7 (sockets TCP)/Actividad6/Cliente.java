package actividad6;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente 
{

	public static void main (String[] args) 
	{
		String host = "localhost";
		int puerto = 6010;

		System.out.println("PROGRAMA CLIENTE INICIANDO");
		System.out.println("Introduce un número:");
		int num = Consola.leeInt();
		
		try 
		{
			Socket cliente = new Socket (host, puerto);
			
			/////// Flujo de salida al servidor
			DataOutputStream flujoSalida = new DataOutputStream (cliente.getOutputStream());

			////// Envío del número entero al servidor
			flujoSalida.write(num);
			
			//// Creación del flujo de entrada al servidor
			DataInputStream flujoEntrada = new DataInputStream (cliente.getInputStream());
			
			////// El servidor envía un mensaje al cliente
			System.out.println("Recibiendo mensaje del servidor:");
			System.out.println("\tEl cuadrado del número " + num + " es " + flujoEntrada.readUTF());

			////// Cerrar streams y sockets
			flujoEntrada.close();
			flujoSalida.close();
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