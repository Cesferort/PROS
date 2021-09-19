package ejemplos3_4_5;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;

public class RenombrarBorrarFicheros 
{
	public static void main (String[] args) 
	{
		FTPClient cliente = new FTPClient (); // cliente
		String servidor = "192.168.0.11"; // Servidor
		String user="dinux";
		String pasw="dinux";
		String direct ="DIRECTORIO";
		try 
		{
			System.out.println("Conectandose a "+ servidor);
			cliente.connect(servidor);
			boolean login=cliente.login(user, pasw);
			if (login) 
			{
				cliente.changeWorkingDirectory(direct);
				// Renombramos fichero1.txt por ficheromodificado.txt
				if (cliente.rename("fichero.txt", "ficherorenombrado.txt")) 
					System.out.println("Fichero renombrado..");
				else 
					System.out.println("No se ha podido renombrar el fichero");
				
				// Borramos el fichero fichero2.jpg
				if (cliente.deleteFile("fichero.jpg"))
					System.out.println("Fichero borrado..");
				else
					System.out.println("No se ha podido borrar el fichero");
				
				cliente.logout();
				cliente.disconnect();
			}
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