import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Actividad5 
{
	
	private static void visualizarConexion(URLConnection con)
	{
		System.out.println("Conexión con " + con.getURL().getHost() + "\n==========================");
		System.out.println("\tMétodo toString(): " + con.toString());
		System.out.println("\tMétodo getDate(): " + con.getDate());
		System.out.println("\tMétodo getContentType(): " + con.getContentType());
		
		System.out.println("\nCampos Cabecera con getHeaderField:\n==========================");
		System.out.println("\tLínea 1: " + con.getHeaderField(0));
		System.out.println("\tLínea 2: " + con.getHeaderField(1));
		System.out.println("\tLínea 3: " + con.getHeaderField(2));
		System.out.println("\tLínea 4: " + con.getHeaderField(3));
		System.out.println("\tLínea 5: " + con.getHeaderField(4));
		
		System.out.println("\nCampos Cabecera con getHeaderFields:\n==========================");
		System.out.println("\tKeep-Alive: " + con.getHeaderField("Keep-Alive"));
		System.out.println("\tnull: " + con.getHeaderField(null));
		System.out.println("\tServer: " + con.getHeaderField("Server"));
		System.out.println("\tConnection: " + con.getHeaderField("Connection"));
		System.out.println("\tContent-Length: " + con.getHeaderField("Content-Length"));
		System.out.println("\tDate: " + con.getHeaderField("Date"));
		System.out.println("\tContent-Type: " + con.getHeaderField("Content-Type"));
		System.out.println("\tLocation: " + con.getHeaderField("Location"));
		
		System.out.println();
	}

	public static void main(String[] args) 
	{
		try 
		{
			URL url = new URL("http", "kaixo.com", 80, "");
			URLConnection con = url.openConnection();
			visualizarConexion(con);
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}