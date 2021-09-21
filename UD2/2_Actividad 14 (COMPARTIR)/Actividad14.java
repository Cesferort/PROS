package Actividad14;

public class Actividad14 extends Thread 
{ 
	public static void main(String args[]) 
	{
		Recurso a = new Recurso(); 
		Recurso b = new Recurso(); 
		Hilo h1 = new Hilo(a, b, "uno");
		//Intercambiamos los recursos a y b para que no ocurra el 
		//abrazo mortal
		Hilo h2 = new Hilo(a, b, "dos");	

		h1.start(); 
		h2.start(); 
	}
}