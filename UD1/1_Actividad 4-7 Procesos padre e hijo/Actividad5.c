/****************************************************************
Módulo: PROS
Trabajo: UD1_Actividades 4-7 (Estructuras de procesos en C)
Actividad: 5
Enunciado: Haz un programa C que genere la siguiente estructura 
	de procesos: Padre -> Hijo1 -> Hijo2 -> ..... -> Hijo n
****************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

void main ()
{	
	pid_t pid, pidPadre;

	int n = 20; //Damos a n valor 20 como ejemplo. Este programa debería crear 20 hijos al proceso padre
	pid = fork(); //Creamos un primer proceso hijo antes de entrar al bucle
	for(int i = 1; i <= n; i++)
	{
		switch(pid)
		{
			case -1: //Comprobamos si el proceso hijo se ha creado correctamente (valor igual a -1)
				printf("No se ha podido crear al hijo %d\n", i);
				exit(-1); //Cerramos el proceso mostrando que ha ocurrido un error
			case 0: //Comprobamos si estamos en el proceso hijo (valor igual a 0)
				printf("Soy el hijo %d, Mi padre es %d y mi PID es %d\n", i, getppid(), getpid());
				pid = fork(); //Creamos un hijo al proceso hijo
				break; //Nos saltamos el resto del código dentro del switch
			default: //Solo entraremos si nos encontramos en el padre
				pidPadre = wait(NULL); //Esperamos a que termine el hijo del proceso en el que nos encontramos
				exit(0); //Cerramos el proceso
		}
	}
	pidPadre = wait(NULL); //Esperamos a que todos los procesos por debajo del padre terminen
	exit(0); //Cerramos el proceso padre
}