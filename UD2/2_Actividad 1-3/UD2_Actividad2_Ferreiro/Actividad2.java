package Actividad2;
public class Actividad2 
{
	public static void main(String arg[]) 
	{
		try
		{
			final int N = Math.abs(Integer.parseInt(arg[0]));
			
			//Creamos un array de N objetos Hilo que serán instanciados y cuya ejecución será inicializada.
			//A parte de eso notificaremos 20 veces la ejecución de cada hilo
			Hilo arrHilos[] = new Hilo[N];
			for(int i = 0; i < arrHilos.length; i++)
			{
				arrHilos[i] = new Hilo();
				arrHilos[i].start();
				for (int j = 0; j < 20; j++)
					System.out.println("Hilo " + (i+1));
			}
			
			try 
			{
				//Esperearemos a que todas las instancias de Hilo dentro del array terminen su ejecución
				for(int i = 0; i < arrHilos.length; i++)
					arrHilos[i].join();	
			} 
			catch (InterruptedException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.print("Final Programa\n");
	}
}