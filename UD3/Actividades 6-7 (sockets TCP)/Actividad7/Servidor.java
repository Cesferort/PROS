package actividad7;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{

	public static void main (String[] args) throws IOException 
	{
		int puerto = 6013;
		ServerSocket servidor = new ServerSocket (puerto, 3);
		
		System.out.println("Esperando a los clientes...");
		
		for(int contSockets = 0; contSockets < 3; contSockets++)
		{
			Socket cli = servidor.accept();
		
			OutputStream os = cli.getOutputStream();
			DataOutputStream dos = new DataOutputStream (os);
			dos.writeUTF("Hola cliente " + (contSockets+1));
		
			os.close();
			dos.close();
		}
		servidor.close();
	} 
}