package Actividad10;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
// Servidor.java
public class Servidor {

		public static void main (String[] args) throws IOException {
			
			int Puerto = 6000;
		
			// Crear socket de servidor
			ServerSocket servidor = new ServerSocket (Puerto);
			
			// Bucle infinito escucha peticiones clientes
			while (true) {
				Socket cliente = new Socket ();
				cliente = servidor.accept();
				System.out.println("Cliente Conectado.....");
				HiloServidor hilo = new HiloServidor (cliente);
				hilo.start();
			
			}
		}
}
