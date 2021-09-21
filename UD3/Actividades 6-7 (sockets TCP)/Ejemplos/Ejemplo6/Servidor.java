package ejemplo6;
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
		int puerto = 6000;
		ServerSocket servidor = new ServerSocket (puerto);
		Socket clienteConectado = null;

		System.out.println("Esperando al cliente.....");
		clienteConectado = servidor.accept();

		////// Creación del flujo de entrada del cliente
		InputStream entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream (entrada);

		////// El cliente envía un mensaje al servidor
		System.out.println("Recibiendo mensaje del cliente: \n\t" + flujoEntrada.readUTF());

		////// Flujo de salida hacia el cliente
		OutputStream salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream (salida);

		////// Envio un saludo al Cliente
		flujoSalida.writeUTF("Saludos al cliente desde el servidor");

		////// Cerrar streams y sockets
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		clienteConectado.close();
		servidor.close(); 
	} 
}