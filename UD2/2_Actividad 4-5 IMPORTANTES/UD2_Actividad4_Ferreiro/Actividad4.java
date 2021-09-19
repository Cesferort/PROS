package Act4;

public class Actividad4 
{

	public static void main(String[] args) 
	{
		Thread p = new Thread(new Proceso(),"primero");
		Thread s = new Thread(new Proceso(), "segundo");	
		p.start();
		s.start();
		System.out.println("Fin programa");
	}
}