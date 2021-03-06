import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo2 
{
	public static void main(String[] args) 
	{
		Runtime r = Runtime.getRuntime();
		String comando = "CMD /C DIR";
		Process p = null;
		
		try 
		{
			p = r.exec(comando);
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea;
			while((linea = br.readLine()) != null) 		// lee una linea
				System.out.println();
			br.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		// Comprobacion: Valor = 0 bien, Valor = - 1 mal
		int exitVal;
		try
		{
			exitVal = p.waitFor();
			System.out.println("Valor de Salida "+exitVal);
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
	}
}