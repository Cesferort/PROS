package ejemplo7;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente 
{

	public static void main (String[] args) throws Exception
	{
		int puerto = 12346;

		InetAddress destino = InetAddress.getLocalHost();

		// Matriz de bytes
		byte[] mensaje = new byte[1024]; 

		//Mensaje a enviar al servidor
		String saludo = "Enviando Saludos!!"; 

		// Codificar el string en bytes
		mensaje = saludo.getBytes(); 

		// Construir el datagrama a enviar al servidor
		DatagramPacket envio = new DatagramPacket (mensaje, mensaje.length, destino, puerto);
		DatagramSocket socket = new DatagramSocket(34567); // Puerto local

		// Visualizar información del datagrama enviado
		System.out.println("Enviando el datagrama de longitud: " + mensaje.length);
		System.out.println("Host Destino: " + destino.getHostName());
		System.out.println("IP destino: " + destino.getHostAddress());
		System.out.println("Puerto local del socket: " + socket.getLocalPort());
		System.out.println("Puerto al que envio: " + envio.getPort());

		// Enviar el datagrama al servidor
		socket.send(envio);
		
		// Cerrar el socket de cliente
		socket.close();
	} 
}