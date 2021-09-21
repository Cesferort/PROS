//COMPROBAR SI EXISTE UN FILE
/*
if(access("Fifo", F_OK ) != -1 ) 						//comprobar si existe el archivo
	remove("Fifo");	
*/

//PROCESOS
/*
getppid() 		Identificador del proceso padre
getpid()		Identificador del proceso
printf("Soy el hijo %d, Mi padre es %d y mi PID es %d\n", i, getppid(), getpid());
*/

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

//COMPILACIÓN DESDE LÍNEA DE COMANDOS
//C:\Program Files (x86)\Java\jdk-15\bin> javac Actividad11.java