package actividad1;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;

public class Actividad1 
{
	public static void main (String [] args) 
	{
		FTPClient cliente = new FTPClient();
		String servFTP = "ftp.rediris.es"; 

		System.out.println("Nos conectamos a " + servFTP);
		String usuario = "anonymous";
		String clave = "dm2";
		
		try 
		{
			// Conectamos a la dirección establecida
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
	        boolean directorioCreado = cliente.makeDirectory("DM2PROS");
	        if (directorioCreado) 
	            System.out.println("Directorio creado....");
	        else 
	            System.out.println("No se ha podido crear el directorio");
	        
	        // Intentamos cerrar sesión y comprobamos si
	        // ha sido llevado a cabo correctamente
			boolean logout = cliente.logout();
			if (logout) 
				System.out.println("Logout del servidor FTP...");
			else
				System.out.println("Error al hacer un Logout..."); 
			
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