package Act4;

public class Proceso implements Runnable 
{
	public synchronized void run() 
	{
		for(int y = 1; y < 100; y++) 
		{
			System.out.println("Hilo "+ Thread.currentThread().getName()+ " "+ y);	
		}
	}
}