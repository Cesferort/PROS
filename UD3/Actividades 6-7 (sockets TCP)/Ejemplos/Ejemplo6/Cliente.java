package ejemplo6;
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
		int puerto = 6000;

		System.out.println("PROGRAMA CLIENTE INICIANDO");
		try 
		{
			Socket cliente = new Socket (host, puerto);
			
			/////// Flujo de salida al servidor
			DataOutputStream flujoSalida = new DataOutputStream (cliente.getOutputStream());

			////// Envío de un saludo al servidor
			flujoSalida.writeUTF("Saludos al servidor desde el cliente");
			
			//// Creación del flujo de entrada al servidor
			DataInputStream flujoEntrada = new DataInputStream (cliente.getInputStream());

			////// El servidor envía un mensaje al cliente
			System.out.println("Recibiendo mensaje del servidor: \n\t" + flujoEntrada.readUTF());

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