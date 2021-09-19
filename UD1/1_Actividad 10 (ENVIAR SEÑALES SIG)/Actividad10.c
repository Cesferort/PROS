#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <signal.h>
#include <sys/wait.h>

/*Simple código a llamar al recibir señal*/
void manejador (int signal)
{
	printf ("Señal recibida...%d\n", signal);
}

void main ()
{ 
	int pid_hijo;
	pid_hijo = fork(); 							//Creación del hijo
	switch (pid_hijo) 							
	{
    	case -1:	//No se ha creado al hijo
			printf("No se ha podido crear el proceso hijo...\n");
			exit(-1);
    	case 0:   	//Nos encontramos en el hijo			
			kill(getppid(), SIGUSR1);			//Enviar señal al padre
			sleep(1);							//Esperar 1 segundo
			kill(getppid(), SIGUSR1);
			sleep(1);
			kill(getppid(), SIGUSR1);
			sleep(1);

			kill(getppid(), SIGKILL);			//Matar al padre
			exit(0);
		default: 	//Nos encontramos en el padre			
			signal (SIGUSR1, manejador); 		//Recibir señal y correr el código "manejador"
			wait(NULL);							
	}
	exit(0);
}