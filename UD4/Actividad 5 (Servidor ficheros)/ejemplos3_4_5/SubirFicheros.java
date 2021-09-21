package ejemplos3_4_5;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;

public class SubirFicheros 
{

	public static void main (String[] args) 
	{
		FTPClient cliente = new FTPClient (); // cliente
		String servidor = "192.168.0.11"; // Servidor
		String user="dinux";
		String pasw="dinux";
		String direct ="DIRECTORIO";
		String fichero ="C:/temp/fichero.txt";
		String ficherodestino="fichero.txt";
		String fichero2 ="C:/temp/fichero.jpg"; 
		String ficherodestino2="fichero.jpg";
		try 
		{
			System.out.println("Conectándose a "+ servidor);
			cliente.connect(servidor);
			boolean login=cliente.login(user, pasw);
			if (login) 
			{
				cliente.changeWorkingDirectory(direct);
				cliente.setFileType(FTP.BINARY_FILE_TYPE);
	
				// Stream de entrda con el fichero a subir
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(fichero));
				cliente.storeFile(ficherodestino, in);

				// Stream de entrada con el fichero a subir
				in = new BufferedInputStream(new FileInputStream(fichero2));
				cliente.storeFile(ficherodestino2, in);
				
				System.out.println("Ficheros subidos");
				in.close();
				
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