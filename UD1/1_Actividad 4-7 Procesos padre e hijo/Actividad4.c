/****************************************************************
Módulo: PROS
Trabajo: UD1_Actividades 4-7 (Estructuras de procesos en C)
Actividad: 4
Enunciado: Haz un programa C que genere una estructura de 
	procesos con un padre y 3 hijos. Visualiza por cada hijo su 
	PID y el del padre. Visualiza también el PID del padre de 
	todos.
****************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

void main ()
{	
	pid_t pid, pidPadre;
	
	for(int i = 1; i <= 3; i++)
	{
		pid = fork(); //Cada vez que el bucle llegue a esta línea generamos un nuevo hijo al proceso padre
		switch(pid)
		{
			case -1: //Comprobamos si el proceso hijo se ha creado correctamente (valor igual a -1)
				printf("No se ha podido crear al hijo %d\n", i);
				exit(-1); //Cerramos el proceso mostrando que ha ocurrido un error
			
			case 0: //Comprobamos si estamos en el proceso hijo (valor igual a 0)
				/*
				getppid() 		Identificador del proceso padre
				getpid()		Identificador del proceso
				*/
				printf("Soy el hijo %d, Mi padre es %d y mi PID es %d\n", i, getppid(), getpid());
				exit(0); //Cerramos el proceso hijo
		}
	}
	pidPadre = wait(NULL); //Eperamos a que terminen los procesos hijo
	printf("Proceso padre %d\n", getpid());
	
	exit(0); //Cerramos el proceso padre
}