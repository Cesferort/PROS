package actividad9;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor 
{

	public static void main (String[] args) 
	{		
		try 
		{
			int puerto = 12348;
			byte bufferRecibo[] = new byte[1024];
			DatagramPacket recibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
			DatagramSocket socket = new DatagramSocket(puerto);
						
			System.out.println("Esperando datagrama.......");
			socket.receive(recibo);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bufferRecibo);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Tenista tenista = (Tenista)ois.readObject();
			bais.close();
			ois.close();
			System.out.println("Recibo el objeto: " + tenista.getApellido() + " " + tenista.getAltura());
			
			System.out.println("IP de origen: " + recibo.getAddress());
			System.out.println("Puerto de origen: " + recibo.getPort());
			
			tenista.setApellido("Karlovic");
			tenista.setAltura(208);
			System.out.println("Envío del objeto: " + tenista.getApellido() + " " + tenista.getAltura()); 
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(tenista);
			byte bufferEnvio[] = baos.toByteArray();
			baos.close();
			oos.close();
			
			DatagramPacket envio = new DatagramPacket(bufferEnvio, bufferEnvio.length, InetAddress.getLocalHost(), 34567);
			socket.send(envio);
			
			System.out.println("Fin del servidor");
			socket.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	} 
}