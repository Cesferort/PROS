package actividad6;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{

	public static void main (String[] args) throws IOException 
	{
		int puerto = 6010;
		ServerSocket servidor = new ServerSocket (puerto);

		System.out.println("Esperando al cliente...");
		Socket clienteConectado = servidor.accept();

		////// Creación del flujo de entrada del cliente
		InputStream entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream (entrada);

		////// El cliente envía un mensaje al servidor
		int numRecibido = flujoEntrada.read();

		////// Flujo de salida hacia el cliente
		OutputStream salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream (salida);

		////// Envio un saludo al Cliente
		flujoSalida.writeUTF(String.valueOf((int)Math.pow(numRecibido, 2)));

		////// Cerrar streams y sockets
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		
		clienteConectado.close();
		servidor.close(); 
	} 
}