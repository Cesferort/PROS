package Ejercicio1;

public class Ejercicio1 extends Thread 
{ 
	public static void main(String args[]) 
	{
		Hilo h1 = new Hilo();
		Hilo h2 = new Hilo();
		Hilo h3 = new Hilo();

		h1.start(); 
		h2.start(); 
		h3.start(); 
		
		try 
		{
			h1.join();
			h2.join(); 
			h3.join(); 
		} 
		catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
		}
		
		System.out.println("Programa finalizado.");
	}
}