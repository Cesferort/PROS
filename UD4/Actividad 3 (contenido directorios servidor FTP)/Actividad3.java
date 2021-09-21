package actividad3;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Actividad3
{
	private static FTPClient cliente;
	
	private static void verDir(FTPFile ftpFile)
	{
		String path = ftpFile.getName();		
		System.out.println("Contenido directorio: " + path);
		try 
		{
			FTPFile listaFTPFiles[] = cliente.listFiles(path);
			if(listaFTPFiles.length == 0)
				System.out.println("\tEstá vacío.");
			else
			{
				ArrayList<FTPFile>dirs = new ArrayList<FTPFile>();
				for (int i = 0; i < listaFTPFiles.length; i++) 
				{
					FTPFile f = listaFTPFiles[i];
					System.out.println("\t" + f.getName() + " => " + f.getType());
					if(f.isDirectory() && !f.getName().equals(".") && !f.getName().equals(".."))
						dirs.add(f);
				}
				
				Iterator<FTPFile>itDirs = dirs.iterator();
				while(itDirs.hasNext())
					verDir(itDirs.next());
			}
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void main (String [] args) 
	{
		cliente = new FTPClient();
		String servFTP = "ftp.cica.es";
		String usuario = "anonymous";
		String clave = "anonymous";		
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
	        
			//FTPFile listaDir[] = cliente.listDirectories();
			FTPFile listaFTPFiles[] = cliente.listFiles();
			if(listaFTPFiles.length == 0)
				System.out.println("\tEstá vacío.");
			else
			{
				ArrayList<FTPFile>dirs = new ArrayList<FTPFile>();
				for (int i = 0; i < listaFTPFiles.length; i++) 
				{
					FTPFile f = listaFTPFiles[i];
					System.out.println("\t" + f.getName() + " => " + f.getType());
					if(f.isDirectory() && !f.getName().equals(".") && !f.getName().equals(".."))
						dirs.add(f);
				}
				
				Iterator<FTPFile>itDirs = dirs.iterator();
				while(itDirs.hasNext())
					verDir(itDirs.next());
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