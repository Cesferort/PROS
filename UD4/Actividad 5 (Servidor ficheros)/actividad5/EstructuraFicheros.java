package actividad5;

import java.io.*;

public class EstructuraFicheros implements Serializable {
	private String name; // nombre del directorio
	private String path;// nombre completo : directorio+nombre
	private boolean isDir; // es un directorio ?
	private int numeFich; // n�mero de ficheros del directorio
	private EstructuraFicheros[] lista; // Lista de ficheros y carpetas
 	
	// Constructor 1
	public EstructuraFicheros(String name) throws FileNotFoundException {
		File file = new File(name);
		this.name = file.getName();
		this.path= file.getPath();
		this.isDir = file.isDirectory();
		this.lista = getListaFiles(); // se carga la lista de ficheros y directorios 
		if (file.isDirectory()) {
			File[] ficheros = file.listFiles();
			this.numeFich=0;
			if (!(ficheros == null)) this.numeFich = ficheros.length;
		}
	}
	
	// Constructor 2
	public EstructuraFicheros(String name, String path, boolean isDir, int numF)  {
		this.name = name;
		this.path= path;
		this.isDir = isDir;
		this.numeFich = numF;
	}

	// M�todos get para obtener los valores de los atributos
	public int getNumeFich() {
		return numeFich;
	}
	
	public boolean isDir() {
		return isDir;
	}
	
	public String getPath() {
		return path;
	}

	public EstructuraFicheros[] getLista() {
		return lista;
	}
	
	public String getName() {
		String name_dir = name;
		if (isDir) {
			int l = path.lastIndexOf(File.separator);
			name_dir = path.substring(1+1, path.length());
		}
		return name_dir;
	}
	
	// Se sobrescribe para asociar a todo objeto un texto representativo 
	// Si es un directorio se antepone al nombre la palabra DIR
	public String toString () {
		String nom= this.name;
		if (this.isDir) nom = "(DIR) " + name;
		return nom;
	}

	// M�todo interno para llenar el array con los ficheros y directorios del directorio seleccionado
		EstructuraFicheros[] getListaFiles() {
		EstructuraFicheros[] lista = null;
		String sDirectorio = this.path;
		File f = new File(sDirectorio);
		File[] ficheros = f.listFiles(); // Ficheros del directorio
		int longitud = ficheros.length;
		if (longitud > 0 ) {
			lista = new EstructuraFicheros[longitud];
			// Se recorre el array de ficheros para llenar la lista
			for (int x = 0; x < ficheros.length; x++) {
				EstructuraFicheros elemento;
				String nombre = ficheros[x].getName();
				String path = ficheros[x].getPath();
				boolean isDir = ficheros[x].isDirectory();
				int num=0;
				if (isDir) {
					// C�lculo del n�mero de ficheros
					File [] fic = ficheros[x].listFiles();
					if (!(fic == null)) num = fic.length;
	
				}
				elemento=new EstructuraFicheros (nombre, path, isDir, num);
				lista[x]= elemento; // se va llenando la lista
			}// for
		}// if
		return lista;
			
	}// fin getListaFiles
}