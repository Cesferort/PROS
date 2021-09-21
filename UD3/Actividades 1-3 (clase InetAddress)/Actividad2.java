import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad2 
{

	public static void main(String[] args) 
	{
		String direcciones[] = 
			{
				"google.com",
				"youtube.com",
				"moodle.icjardin.com",
				"web.gencat.cat"
			};
		
		for (int contDireccion = 0; contDireccion < direcciones.length; contDireccion++)
		{
			try 
			{
				String dir = direcciones[contDireccion];
				InetAddress address[] = InetAddress.getAllByName(dir);
				System.out.println("Direcciones asociadas a " +  dir + " son:");
				
				for (int i = 0; i < address.length; i++)
					System.out.println(address[i]);
				
				System.out.println();
			} 
			catch (UnknownHostException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
}