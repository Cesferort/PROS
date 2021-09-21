/****************************************************************
Módulo: PROS
Trabajo: UD1_Actividades 4-7 (Estructuras de procesos en C)
Actividad: 6
Enunciado: Haz un programa C que genere la siguiente estructura 
	de procesos: Padre 	-> Hijo1
						-> Hijo2 -> Hijo3
****************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

void main ()
{	
	pid_t pid, pidPadre;
	
	for(int i = 1; i <= 3; i++) //Bucle que nos permitirá hacer 3 hijos
	{
		if(i<=2) //Entramos solo en caso de que hayamos creado 2 hijos o menos
		{
			pid = fork(); //Creamos un hijo al proceso padre
		}
		switch(pid)
		{
			case -1: //Comprobamos si el proceso hijo se ha creado correctamente (valor igual a -1)
				printf("No se ha podido crear al hijo %d\n", i);
				exit(-1); //Cerramos el proceso mostrando que ha ocurrido un error
			case 0: //Comprobamos si estamos en el proceso hijo (valor igual a 0)
				printf("Soy el hijo %d, mi padre es PID= %d, yo soy PID= %d\n", i, getppid(), getpid());
				if(i == 2) //Entramos solo en caso de que hayamos creado 2 hijos
				{
					i++; //Incrementamos el valor de i pues vamos a crear el tercer hijo y lo 
						 //necesitamos para saber cuantos hijos tenemos creados
					pid = fork(); //Creamos un hijo al proceso hijo numero 2
					switch(pid)
					{
						case -1: //Comprobamos si el proceso hijo se ha creado correctamente (valor igual a -1)
							printf("No se ha podido crear al hijo %d\n", i);
							exit(-1); //Cerramos el proceso mostrando que ha ocurrido un error
						case 0: //Comprobamos si estamos en el proceso hijo (valor igual a 0)
							printf("Soy el hijo %d, mi padre es PID= %d, yo soy PID= %d\n", i, getppid(), getpid());
							exit(0); //Cerramos el proceso hijo
					}
				}
				exit(0); //Cerramos el proceso hijo
		}
	}
	pidPadre = wait(NULL); //Esperamos a que todos los procesos por debajo del padre terminen
	printf("Proceso padre %d\n", getpid()); //A pesar de que no lo pida el ejercicios escribimos el identificador 
											//del padre solo para asegurarnos de que los resultados son correctos
	
	exit(0); //Cerramos el proceso padre
}