package Actividad3;
public class Hilo extends Thread
{
	public void run()
	{
		try 
		{
			sleep(1000);							//Esperamos un segundo para que al código le de tiempo
													//a cambiar el nombre y prioridad del Hilo
		} 
		catch (InterruptedException e) 
		{
			System.out.println(e.getMessage());
		}
	}
}