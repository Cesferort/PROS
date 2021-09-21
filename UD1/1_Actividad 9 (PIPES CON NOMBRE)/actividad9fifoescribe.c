#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

int main (void)
{
	char saludo[] = "Once more unto the breach dear friends, once more.\n";
	int fp = open("Fifo", 1);								// abro el pipe Fifo en modo escritura

	switch(fp)
	{
		case -1:
			printf("No se ha podidod abrir el archivo Fifo con permisos de escritura\n");
			exit(-1);
		default:
			printf("Mandando información al FIFO...\n");
			write(fp, saludo, sizeof(saludo));				// escribimos en el pipe el texto guardado dentro de saludo
			close(fp);
	}
	exit(0);
}