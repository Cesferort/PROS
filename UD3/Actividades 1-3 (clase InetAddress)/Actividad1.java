import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad1 
{

	public static void main(String[] args) 
	{
		InetAddress address[];
		try 
		{
			address = InetAddress.getAllByName("www.spotify.com");
			System.out.println("Direcciones asociadas a Spotify:\n");
			
			for (int i = 0; i < address.length; i++)
				System.out.println(address[i]);
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}