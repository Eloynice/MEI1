#include <mpi.h>
#include <stdio.h>
#include <math.h>

int main(int argc, char** argv) {
	//ctrl l para ajeitar a cmd
    MPI_Init(NULL, NULL);

    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    char processor_name[MPI_MAX_PROCESSOR_NAME];
    int name_len;
    MPI_Get_processor_name(processor_name, &name_len);

    MPI_Status status;

    int Vetor[3] = {1,10,0};   // declara um vetor de 3 posições

    int Matriz[5][3] = {{1,2,3},{4,5,6},{7,8,9},{10,11,12},{13,14,15}}; // declara uma matriz de 5 linhas e 3 colunas

    int result = 0;
    
    if (world_rank == 0){
        printf("\n");
        int final[5][1];
        for(int i = 0; i <= 2; i++){
            printf("%d\n",i);
            result = result + Matriz[0][i] * Vetor[i];
        }
        
        final[0][0] = result;
        int recv = 0;
        
        for (int i = 1; i < world_size; i++) { 
                MPI_Recv(&recv, 1, MPI_INT, MPI_ANY_SOURCE, 10, MPI_COMM_WORLD, &status);
                printf("received: %d\n", recv);
                final[i][0] = recv;
        } 

        printf("MATRIX RESULT!\n");
        for(int i = 0; i < 5; i++){
            printf("%d\n",final[i][0]);
        }
    }
    else{
        int j = world_rank; 
    	for(int i = 0; i <= 2;i++){
    		result = result + Matriz[j][i] * Vetor[i];
    	}
    	MPI_Send(&result,1,MPI_INT,0,10,MPI_COMM_WORLD);
    }

    MPI_Finalize();
}
