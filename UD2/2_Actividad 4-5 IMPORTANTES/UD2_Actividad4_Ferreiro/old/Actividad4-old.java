package Actividad4;
public class Actividad4 
{
	public static void main(String arg[]) 
	{
		Thread thread1 = new Thread(new Posicion());		
		thread1.setName("Primero");
		Thread thread2 = new Thread(new Posicion());
		thread2.setName("Segundo");
		thread1.start();								//Iniciamos la ejecución del Thread instanciado
		thread2.start();
		try 
		{
			thread1.join();			
			thread2.join();								//Esperamos a que el Thread termine su ejecución
		} 
		catch (InterruptedException e) 					//Controlamos una posible excepción
		{
			System.out.println(e.getMessage());
		}
		System.out.print("Fin programa\n");
	}
}