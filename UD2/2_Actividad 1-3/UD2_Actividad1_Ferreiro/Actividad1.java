package Actividad1;

public class Actividad1 
{
	public static void main(String arg[]) 
	{
		Hilo hilo1 = new Hilo();	
		hilo1.setName("Primero");
		Hilo hilo2 = new Hilo();
		hilo2.setName("Segundo");
		
		hilo1.start();			
		hilo2.start();									//Iniciamos la ejecución del Thread instanciado
		try 
		{
			hilo1.join();		
			hilo2.join();								//Esperamos a que el Thread termine su ejecución
		} 
		catch (InterruptedException e) 					//Controlamos una posible excepción
		{
			System.out.println(e.getMessage());
		}
		
		System.out.print("Fin programa\n");
	}
}