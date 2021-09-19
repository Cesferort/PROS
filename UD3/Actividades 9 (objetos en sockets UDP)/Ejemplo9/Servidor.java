package ejemplo9;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor 
{

	public static void main (String[] args) throws ClassNotFoundException 
	{
		int puerto = 6000;
		try 
		{
			ServerSocket servidor = new ServerSocket (puerto);
			System.out.println("ESPERANDO AL CLIENTE... ");
			Socket cliente = servidor.accept();

			// Flujo de salida hacia el cliente
			ObjectOutputStream outObjeto = new ObjectOutputStream (cliente.getOutputStream()); 
			
			// Creación del objeto y envío al cliente
			Persona per = new Persona ("Juan",20);
			outObjeto.writeObject(per);
			System.out.println("Envío: " + per.getNombre() + " * " + per.getEdad());
			
			// Flujo de entrada para leer el objeto
			ObjectInputStream inObjeto = new ObjectInputStream (cliente.getInputStream());
			Persona dato = (Persona) inObjeto.readObject();
			System.out.println("Recibo: " + dato.getNombre() + " * " + dato.getEdad());

			// Cierre de streams y sockets
			outObjeto.close();
			inObjeto.close();
			servidor.close();
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