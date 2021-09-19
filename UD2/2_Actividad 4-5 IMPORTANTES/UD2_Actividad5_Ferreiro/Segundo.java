package Actividad5;
public class Segundo extends Thread
{
	public void run()
	{
		try 
		{
			for (int i = 1; i <= 15;i++)
			{
				System.out.println("Segundo " + i);
				sleep(200);								//Detener la ejecución del bucle durante 0.2 segundos
			}
		} 
		catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}