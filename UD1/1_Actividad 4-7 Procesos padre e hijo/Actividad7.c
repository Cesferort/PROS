/****************************************************************
Módulo: PROS
Trabajo: UD1_Actividades 4-7 (Estructuras de procesos en C)
Actividad: 7
Enunciado: Realiza un programa en C que cree un proceso 
	(tendremos un proceso padre y otro hijo). El programa 
	definirá una variable entera y le dará un valor 6. El 
	proceso padre incrementará dicho valor en 5 y el hijo 
	restará 5. 
****************************************************************/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

void main ()
{	
	pid_t pid, pidPadre;

	int num = 6;
	printf("Valor inicial de la variable: %d\n", num);

	pid = fork(); //Creamos un hijo al proceso padre
	switch (pid)
	{
		case -1: //Comprobamos si el proceso hijo se ha creado correctamente (valor igual a -1)
			printf("No se ha podido crear al hijo\n");
			exit(-1); //Cerramos el proceso mostrando que ha ocurrido un error
		case 0: //Comprobamos si estamos en el proceso hijo (valor igual a 0)
			num -= 5;
			printf("Variable en Proceso Hijo: %d\n", num);
			exit(0); //Cerramos el proceso hijo		
	}
	pidPadre = wait(NULL); 	//Esperamos a que el proceso hijo termine su ejecución
	num += 5;
	printf("Variable en Proceso Padre: %d\n", num);

	exit(0); //Cerramos el proceso padre
}