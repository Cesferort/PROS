package Actividad10;
public class Hilo extends Thread
{
	private String mensaje;
	
	//Método para guardar el mensaje recibido como parámetro
	public void mensajeEjecucion(String mensaje)
	{
		this.mensaje = mensaje;
	}
	
	//Al ser ejecutado el Thread se visualizará por pantalla el mensaje guardado con el 
	//método mensajeEjecución
	public void run()
	{
		System.out.println(mensaje);
	}
}