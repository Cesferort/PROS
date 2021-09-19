package actividad4;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SocketServidorSeguro 
{

	public static void main (String[] args) throws IOException 
	{
		int puerto = 6020;
		SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket servidor= (SSLServerSocket) sfact.createServerSocket(puerto);

		System.out.println("Esperando al cliente...");
		SSLSocket clienteConectado = (SSLSocket)servidor.accept();

		////// Creación del flujo de entrada del cliente
		InputStream entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream (entrada);

		////// El cliente envía un mensaje al servidor
		int numRecibido = flujoEntrada.read();

		////// Flujo de salida hacia el cliente
		OutputStream salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream (salida);

		////// Envio un saludo al SocketClienteSeguro
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