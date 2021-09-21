import java.net.MalformedURLException;
import java.net.URL;

public class Actividad4 
{
	
	public static void visualizar(URL url)
	{
		System.out.println("Método toString(): " + url.toString());
		System.out.println("Método getProtocol(): " + url.getProtocol());
		System.out.println("Método getHost(): " + url.getHost());
		System.out.println("Método getPort(): " + url.getPort());
		System.out.println("Método getFile(): " + url.getFile());
		System.out.println("Método getUserInfo(): " + url.getUserInfo());
		System.out.println("Método getPath(): " + url.getPath());
		System.out.println("Método getAuthority(): " + url.getAuthority());
		System.out.println("Método getQuery(): " + url.getQuery());
		
		System.out.println();
	}

	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Constructor simple para una URL:");
			URL url1 = new URL("http://docs.oracle.com");
			visualizar(url1);
			
			System.out.println("Constructor para protocolo, host y directorio:");
			URL url2 = new URL("http", "docs.oracle.com", "/javase/7");
			visualizar(url2);
			
			System.out.println("Constructor para protocolo, host, puerto y directorio:");
			URL url3 = new URL("http", "docs.oracle.com", 80, "/javase/7/docs/api/java/net/URL.html");
			visualizar(url3);
			
			System.out.println("Constructor para un objeto URL y su directorio:");
			URL url4 = new URL(url1, "/javase/7/docs/api/java/net/URL.html");
			visualizar(url4);
		} 
		catch (MalformedURLException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}