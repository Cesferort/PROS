package Actividad14;

class Hilo extends Thread 
{ 
	Recurso a; 
	Recurso b; 

	public Hilo(Recurso a, Recurso b,String nombre) 
	{ 
		super(nombre); 
		this.a = a; 
		this.b = b; 
	} 

	public void run()
	{ 
		System.out.println("Hilo " + this.getName() + " comienza"); 
		synchronized(a) 
		{ 
			try 
			{ 
				Thread.sleep(100); 
			} 
			catch (InterruptedException e) 
			{ 
				e.printStackTrace(); 
			} 
			
			synchronized(b)
			{ 
			
			} 
			
			System.out.println("Hilo " + this.getName() + " ha terminado"); 
		} 
	} 
}