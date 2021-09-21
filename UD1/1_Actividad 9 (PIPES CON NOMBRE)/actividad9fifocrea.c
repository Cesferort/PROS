#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main (void)
{
	int pipe;
	char buffer[10];

	if(access("Fifo", F_OK ) != -1 ) 						//comprobar si existe el archivo
		remove("Fifo");										//en caso de que si lo borramos
	pipe = mknod("Fifo", S_IFIFO|0666, 0);
	switch (pipe) 
	{
		case -1:
			printf("No se ha podido crear el pipe\n"); 		
			exit (0);
		default:
			for (;;)										// creo bucle infinito					
			{				 					
				int fp = open ("Fifo", 0);					// abro el pipe Fifo en modo lectura y
				int bytesleidos = read(fp, buffer, 1); 		// leo el primer byte
				printf("Obteniendo información...\n"); 
				while (bytesleidos != 0) 
				{
					printf("%s", buffer);
					bytesleidos = read(fp, buffer, 1); 		// leo otro byte
				}
				close (fp);
			}
	}
	exit(0);
}