package Actividad4;
public class Posicion implements Runnable
{
	
	public void run()
	{
		for (int i = 1; i <= 15;i++)
		{
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}