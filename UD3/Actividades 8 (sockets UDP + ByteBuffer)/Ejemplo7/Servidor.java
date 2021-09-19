package ejemplo7;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor 
{

	public static void main (String[] args) throws Exception 
	{
		int puerto = 12346;

		// Buffer para recibir los datagramas
		byte[] bufer = new byte[1024]; 
		
		// Asociar el socket al puerto 12346 del servidor
		DatagramSocket socket = new DatagramSocket(puerto);

		// Esperar la llegada del datagrama al servidor
		System.out.println("Esperando datagrama.......");
		DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
		
		// Recibir el datagrama
		socket.receive(recibo); 
		
		// Obtener el número de bytes del datagrama
		int bytesRec = recibo.getLength(); 
		
		// Obtener el datagrama en String
		String paquete = new String(recibo.getData()); 

		// Visualizar información del datagrama recibido
		System.out.println("Número de bytes recibidos: " + bytesRec);
		System.out.println("Contenido del paquete: " + paquete.trim());
		System.out.println("Puerto de origen del mensaje: " + recibo.getPort());
		System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());
		System.out.println("Puerto destino del mensaje: " + socket.getLocalPort());

		//Cerrar el socket de servidor
		socket.close();
	} 
}