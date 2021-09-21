package ejemplo1;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP1 
{

	public static void main (String [] args) throws IOException 
	{
		FTPClient cliente = new FTPClient();
		String servFTP = "ftp.rediris.es"; // servidor FTP
		
		// Respuesta del servidor FTP
		System.out.println("Nos conectamos a " + servFTP);
		cliente.connect(servFTP);
		System.out.println(cliente.getReplyString());

		// Código de respuesta
		int respuesta = cliente.getReplyCode();

		// Comprobación del codigo de respuesta
		if (!FTPReply.isPositiveCompletion(respuesta)) 
		{
			cliente.disconnect();
			System.out.println("Conexión rechazada:" + respuesta);
			System.exit(0);
		}
		// Desconexión del servidor FTP
		cliente.disconnect();
		System.out.print("Conexión finalizada");
	}
}