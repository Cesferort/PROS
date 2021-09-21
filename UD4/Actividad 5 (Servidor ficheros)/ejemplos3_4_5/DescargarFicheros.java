package ejemplos3_4_5;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;

public class DescargarFicheros 
{

	public static void main (String[] args) 
	{
		FTPClient cliente = new FTPClient (); // cliente
		String servidor = "192.168.0.11"; // Servidor
		String user="dinux";
		String pasw="dinux";
		String direct ="DIRECTORIO";
		String origen="ficherorenombrado.txt";
		String destino="C:/temp/ficherorenombrado.txt";
		try 
		{
			System.out.println("Conectandose a "+ servidor);
			cliente.connect(servidor);
			boolean login=cliente.login(user, pasw);
			if (login) 
			{
				cliente.changeWorkingDirectory(direct); 
				// Stream de salida para recibir el fichero descargado
				BufferedOutputStream out = new BufferedOutputStream (new FileOutputStream (destino)); 
				if (cliente.retrieveFile(origen, out))
					System.out.println("Fichero descargado..");
				else
					System.out.println("No se ha podido descargar el fichero");

				out.close();
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