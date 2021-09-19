package actividad2;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;

public class Actividad2
{
	public static void main (String [] args) 
	{
		FTPClient cliente = new FTPClient();
		String servFTP = "192.168.0.11";

		System.out.println("Nos conectamos a " + servFTP);
		String usuario = "dinux";
		String clave = "dinux";
		
		try 
		{
			System.out.println("Nos conectamos a " + servFTP);
			cliente.connect(servFTP);
			
			// Hacemos un intento de inicio de sesión 
			boolean login = cliente.login(usuario, clave);
			
			// Validamos el resultado del inicio de sesión
			if (login)
				System.out.println("Login correcto");
			else 
			{
				System.out.println("Login incorrecto...");
				cliente.disconnect();
				System.exit(1);
			}
			
			// Visualizamos directorio actual
			System.out.println("Directorio actual: " + cliente.printWorkingDirectory());

			// Creamos un nuevo direcctorio y comprobamos si
			// la creación ha sido llevada a cabo correctamente
	        boolean directorioCreado = cliente.makeDirectory("aplicacionwebferreiro");
	        boolean directoriosCreados = true;
	        if (directorioCreado) 
	        {
	            if(directorioCreado = cliente.makeDirectory("aplicacionwebferreiro/html"))
	            {
		            if(directorioCreado = cliente.makeDirectory("aplicacionwebferreiro/imagenes"))
		            {
			            if(!(directorioCreado = cliente.makeDirectory("aplicacionwebferreiro/css")))
			            	directoriosCreados = false;
		            }
		            else
		            	directoriosCreados = false;
	            }
	            else
	            	directoriosCreados = false;
	        }
	        else 
	        	directoriosCreados = false;
	        
	        if(directoriosCreados == true)
	        	System.out.println("Directorios creados...");
	        else
	        {
	        	System.out.println("NO SE HAN PODIDO CREAR LOS DIRECTORIOS");
				cliente.disconnect();
				System.exit(1);
	        }
	        
	        // Intentamos cerrar sesión y comprobamos si
	        // ha sido llevado a cabo correctamente
			boolean logout = cliente.logout();
			if (logout) 
				System.out.println("Logout del servidor FTP...");
			else
				System.out.println("Error al hacer logout"); 
			
			// Desconectamos del servidor
			cliente.disconnect();
			System.out.println("Desconectado...");
		} 
		catch (SocketException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}