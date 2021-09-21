package Actividad3;
public class Actividad3 
{
	public static void main(String arg[]) 
	{
		//Instanciamos y ejecutamos un Hilo
		Hilo hilo = new Hilo();
		hilo.start();
		
		//Visualizamos datos
		System.out.println("El nombre del hilo es " + hilo.getName() + " y tiene la prioridad " + hilo.getPriority());
		
		//Hacemos cambios en el nombre y prioridad del Hilo
		hilo.setName("SUPER-HILO-DM2");
		hilo.setPriority(6);
		
		//Visualizamos datos
		System.out.println("Ahora el nombre del hilo es " + hilo.getName() + " y tiene la prioridad " + hilo.getPriority());
		
		System.out.print("Final Programa\n");
	}
}