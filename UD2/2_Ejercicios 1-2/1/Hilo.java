package Ejercicio1;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Hilo extends Thread 
{ 
	private DateFormat dateFormat;
	private Date date;
	
	public Hilo()
	{
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		date = new Date();
	}
	
	public void run()
	{ 
		for(int i = 0;i < 5;i++)
		{
			System.out.println(Thread.currentThread().getName()+ " - " + dateFormat.format(date));
			try 
			{
				sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	} 
}