package Actividad10;
public class Actividad10 extends Thread
{
	//Método que asigna la prioridad recibida al thread indicado
	private static void asignarPrioridad(Thread thread, int prioridad)
	{
		thread.setPriority(prioridad);
	}
	
	public void run()
	{
		//Instanciamos los Hilos
		Hilo hilo1 = new Hilo();			
		Hilo hilo2 = new Hilo();
		
		//Con las instanciaciones de Hilo creamos Threads
		Thread thread1 = new Thread(hilo1);	
		Thread thread2 = new Thread(hilo2);	
		
		//Le damos el nombre que deseamos a cada Thread
		setName("main");
		thread1.setName("Thread-0");
		thread2.setName("Thread-1");
		
		//Utilizamos el método asignarPrioridad sobre los Threads
		asignarPrioridad(thread1, 3);
		asignarPrioridad(thread2, 7);

		//Mostramos por pantalla información sobre los Threads
		System.out.println(getName() + " tiene la prioridad " + getPriority());
		System.out.println(thread1.getName() + " tiene la prioridad " + thread1.getPriority());
		System.out.println(thread2.getName() + " tiene la prioridad " + thread2.getPriority());
		
		//Enviamos a los Thread la información que deseamos que visualicen
		hilo1.mensajeEjecucion("Ejecutando Hilo-prioridad " + thread1.getPriority());
		hilo2.mensajeEjecucion("Ejecutando Hilo-prioridad " + thread2.getPriority());
		
		//Iniciamos la ejecución de los Thread
		thread1.start();									
		thread2.start();									
				
		try 
		{
			//Esperamos a que los Thread terminen su ejecución para una más limpia visualización
			thread1.join();									
			thread2.join();									
		} 
		//Controlamos una posible excepción
		catch (InterruptedException e) 						
		{
			System.out.println(e.getMessage());
		}
		System.out.print("Final programa\n");
	}
	
	public static void main(String arg[]) 
	{
		Actividad10 prueba = new Actividad10();
		prueba.start();
	}
}