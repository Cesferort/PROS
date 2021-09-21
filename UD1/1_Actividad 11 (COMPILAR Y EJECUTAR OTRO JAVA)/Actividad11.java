import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Actividad11 
{
    public static void main(String[] args) 
    {
    	try 
    	{
    		ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo2");
    	    pb.redirectError();
    	    pb.directory(new File("src"));
    	    Process proc = pb.start();									//Inicializar proceso
    		InputStream input = proc.getInputStream();					//Guardar la salida textual en InputStream
			
			String output = "";
            int in;
            try 
            {
            	//Guardar en un String todo el InputStream
                while ((in = input.read()) != -1) 						
                	output += (char)in;
			}
	    	catch (IOException e) 
	    	{
				System.out.println(e.getMessage());
			}
            
            System.out.println(output);
    	}	
    	catch (IOException e) 
    	{
			System.out.println(e.getMessage());
		}	
    }
}