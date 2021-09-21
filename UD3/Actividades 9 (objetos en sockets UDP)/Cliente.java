package actividad9;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Cliente 
{
	
	public static void main (String[] args) 
	{
		try 
		{
			int puerto = 12348;
			InetAddress destino = InetAddress.getLocalHost();
			
			Tenista tenistaEnviar = new Tenista("del Potro", 198);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(tenistaEnviar);
			byte bufferEnvio[] = baos.toByteArray();
			baos.close();
			oos.close();
			
			DatagramPacket envio = new DatagramPacket(bufferEnvio, bufferEnvio.length, destino, puerto);
			DatagramSocket socket = new DatagramSocket(34567);
			socket.send(envio);
			System.out.println("Envío el objeto: " + tenistaEnviar.getApellido() + " " + tenistaEnviar.getAltura());
						
			byte bufferRecibo[] = new byte[bufferEnvio.length];
			DatagramPacket recibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
			System.out.println("Esperando datagrama.......");
			socket.receive(recibo);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(bufferRecibo);
			ObjectInputStream ois = new ObjectInputStream(bais);			
			Tenista tenistaRecibir = (Tenista)ois.readObject();
			bais.close();
			ois.close();
			System.out.println("He recibido el objeto: " + tenistaRecibir.getApellido() + " " + tenistaRecibir.getAltura());
			
			System.out.println("Fin del cliente");
			socket.close();
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
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