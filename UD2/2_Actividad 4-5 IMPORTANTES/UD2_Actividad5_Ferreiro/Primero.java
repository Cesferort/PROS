package Actividad5;
public class Primero extends Thread
{
	public void run()
	{
		try 
		{
			for (int i = 1; i <= 15;i++)
			{
				System.out.println("Primero " + i);
				sleep(100);								//Detener la ejecución del bucle durante 100 milisegundos
			}
		} 
		catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}