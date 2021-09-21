#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

void main ()
{	
	int fd1[2],fd2[2];
	char buffer[80];
	pid_t pid, pid2;

	pipe(fd1);
	pipe(fd2);
	pid = fork();
	switch(pid)
	{
		case -1:
			printf("No se ha podido crear el proceso HIJO\n");
			exit(-1);
		case 0:						//HIJO
			pid2 = fork();
			switch(pid2)
			{
				case -1:
					printf("No se ha podido crear el proceso NIETO\n");
					exit(-1);
				case 0: 			//NIETO
					//RECIBE DEL HIJO
					close(fd2[1]);
					read(fd2[0], buffer, sizeof(buffer));
					printf("\t\tEl NIETO recibe mensaje del padre: %s\n", buffer);
					
					//ENVIAR HIJO
					close(fd1[0]);
					char saludoNieto[] = "Saludo del nieto";
					write(fd1[1], saludoNieto, sizeof(saludoNieto));
					printf("\t\tEl NIETO envía un mensaje al HIJO\n");

					exit(0);
				default: 			//HIJO
					//RECIBE DEL PADRE
					close(fd1[1]);
					read(fd1[0], buffer, sizeof(buffer));
					printf("\tEl HIJO recibe mensaje del ABUELO: %s\n", buffer);
					
					//ENVIAR AL NIETO
					char saludoHijo[] = "Saludo del padre";
					write(fd2[1], saludoHijo, sizeof(saludoHijo));
					printf("\tEl HIJO envía un mensaje al NIETO......\n");

					wait(NULL);

					//RECIBE DEL NIETO
					close(fd1[1]);
					read(fd1[0], buffer, sizeof(buffer));
					printf("\tEL HIJO recibe mensaje de su hijo: %s\n", buffer);
					
					//ENVIAR AL ABUELO
					char saludoHijo2[] = "Saludo del hijo";
					close(fd2[0]);
					write(fd2[1], saludoHijo2, sizeof(saludoHijo2));
					printf("\tEl HIJO envia un mensaje al ABUELO......\n");
						
					exit(0);
			}
			break;
		default: 					//ABUELO
			//ENVIAR AL HIJO
			close(fd1[0]);
			char saludoAbuelo[] = "Saludo del abuelo..";
			write(fd1[1], saludoAbuelo, sizeof(saludoAbuelo));	
			printf("El ABUELO envía un mensaje al HIJO.......\n");
			
			wait(NULL);

			//RECIBE DEL HIJO
			close(fd2[1]);
			read(fd2[0], buffer, sizeof(buffer));
			printf("El ABUELO recibe mensaje del HIJO: %s\n", buffer);
	}
	exit(0);
}