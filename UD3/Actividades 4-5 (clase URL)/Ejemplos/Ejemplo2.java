import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo2 
{

	public static void main(String[] args) 
	{
		try 
		{
			// Obtener URL Absoluta
			// Base URL = www.gnu.org
			URL url1 = new URL("http://www.gnu.org");
			System.out.println("URL1: " + url1.toString());

			// Generar URL a partir de una URL base
			URL url2 = new URL(url1, "licenses/gpl.txt");
			System.out.println("URL2: " + url2.toString());

			// Generar URL a partir del protocolo, host y fichero
			URL url3 = new URL("http", "www.gnu.org", "/licenses/gpl.txt");
			System.out.println("URL3: " + url3.toString());

			// Generar URL a partir del protocolo, host, puerto y fichero
			URL url4 = new URL("http", "www.gnu.org", 80, "/licenses/gpl.txt");
			System.out.println("URL4: " + url4.toString());

			// Abrir el buffer de lectura a partir de la URL
			try 
			{
				// Leemos el fichero "gpl.txt" 
				System.out.println("\n/***** Contenido del fichero URL4 *****\n");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(url4.openStream()));
				String inputLine;
				while((inputLine = br.readLine()) != null) 
					System.out.println(inputLine);
			} 
			catch (IOException e) 
			{
				System.out.println(e.getMessage());
			}
		} 
		catch (MalformedURLException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}