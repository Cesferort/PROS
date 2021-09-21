package Actividad5;
public class Actividad5 
{
	public static void main(String arg[]) 
	{
		Thread p = new Thread(new Primero());	
		Thread s = new Thread(new Segundo());
		p.start();										//Iniciamos la ejecución del Thread instanciado
		s.start();										//Iniciamos la ejecución del Thread instanciado
		try 
		{
			p.join();									//Esperamos a que el Thread termine su ejecución
			s.join();									//Esperamos a que el Thread termine su ejecución
		} 
		catch (InterruptedException e) 					//Controlamos una posible excepción
		{
			System.out.println(e.getMessage());
		}
		
		System.out.print("Fin programa\n");
	}
}