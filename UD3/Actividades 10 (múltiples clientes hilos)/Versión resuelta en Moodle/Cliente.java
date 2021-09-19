package Actividad10;

//Cliente.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main (String[] args) {

		String cadena, entrada;
		String Host="localhost";
		int Puerto = 6000;

		System.out.println("PROGRAMA CLIENTE INICIANDO");

		try {

			Socket Cliente = new Socket (Host, Puerto);

			// Flujo para la cadena estandard teclado
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			System.out.print("Introduce una cadena:");
			cadena = in.readLine(); // Lectura teclado

			// Creación del flujo de entrada a enviar al servidor
			BufferedReader fentrada = new BufferedReader (new InputStreamReader (Cliente.getInputStream()));

			// Flujo de salida al servidor
			PrintWriter fsalida = new PrintWriter(Cliente.getOutputStream(), true);
			while (true)
				
			{

				fsalida.println(cadena); // Envio cadena al servidor

				entrada=fentrada.readLine(); // Lectura cadena del servidor

				System.out.println(" =>Respuesta:"+entrada);

				if (cadena.equals("*"))

					break;

				System.out.println("Introduce cadena:");

				cadena = in.readLine(); // Lectura teclado

			} // Fin del while

			// Cierre de streams y sockets
			fsalida.close();
			fentrada.close();
			System.out.println("Fin del envío....");
			Cliente.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}
} 
