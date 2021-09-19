import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad3 
{

	public static void main(String[] args) 
	{
		String direcciones[] = 
			{
				"google.com",
				"direccion.inexistente"
			};
		
		boolean hayDireccionesValidas = false;
		for (int contDireccion = 0; contDireccion < direcciones.length; contDireccion++)
		{
			try 
			{
				String dir = direcciones[contDireccion];
				InetAddress address[] = InetAddress.getAllByName(dir);
				
				System.out.println("Dirección IP: " + address[0].getHostAddress());
				System.out.println("Nombre: " + address[0].getHostName());
				for (int i = 0; i < address.length; i++)
					System.out.println(address[i]);
				
				hayDireccionesValidas = true;
				System.out.println();
			} 
			catch (UnknownHostException e) {}
		}
		if(hayDireccionesValidas == false)
		{
			InetAddress local;
			InetAddress localAddresses[];
			try 
			{
				local = InetAddress.getLocalHost();
				localAddresses = InetAddress.getAllByName(local.getHostName());
				
				System.out.println("Dirección IP: " + localAddresses[0].getHostAddress());
				System.out.println("Nombre: " + localAddresses[0].getHostName());
				for (int i = 0; i < localAddresses.length; i++)
					System.out.println(localAddresses[i]);
			} 
			catch (UnknownHostException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
}