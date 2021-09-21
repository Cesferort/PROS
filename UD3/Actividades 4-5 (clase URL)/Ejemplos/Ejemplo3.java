import java.io.*;
import java.net.*;

public class Ejemplo3 
{

	public static void main(String argv[]) 
	{
		URL url = null;
		try 
		{
			url = new URL ("http://www.euskadi.net");
			URLConnection con = url.openConnection();

			System.out.println("Cargando " + url);
			
			DataInputStream dis = new DataInputStream(con.getInputStream());
			BufferedReader br = new BufferedReader (new InputStreamReader(dis));

			String inputLine;
			while ((inputLine = br.readLine()) != null)
				System.out.println(inputLine);

			br.close();
		}
		catch (MalformedURLException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (UnknownHostException e) 
		{
			System.out.println("El host no existe o no responde");
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}