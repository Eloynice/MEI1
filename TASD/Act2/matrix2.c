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

    int recv = 0;
    int result = 0;
    int receives = 0;
    printf("%d\n",world_size);

    if (world_rank == 0){
        printf("\n");
        int final[5][1];
        for(int i = 0; i <= 2; i++){
            result = result + Matriz[0][i] * Vetor[i];
            receives++;
        }
        
        final[0][0] = result;
        
        for (int i = 1; i < world_size; i++) { 
                MPI_Recv(&recv, 1, MPI_INT, MPI_ANY_SOURCE, 10, MPI_COMM_WORLD, &status);
                printf("received: %d\n", recv);
                final[i][0] = recv;
                receives++;
        }

        if(world_size < 5){
            int r = receives;
            while(receives != 5){
                for(int i = 1; i < world_size; i++){
                    MPI_Send(&r,1,MPI_INT,i,10,MPI_COMM_WORLD);
                    r++;
                }
                for(int i = 1; i < world_size; i++){
                    MPI_Recv(&recv, 1, MPI_INT, MPI_ANY_SOURCE, 10, MPI_COMM_WORLD, &status);
                    printf("received: %d\n", recv);
                    final[i][0] = recv;
                    receives++;
                }

            }
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
        while(world_size < 5){
            MPI_Recv(&recv, 1, MPI_INT, MPI_ANY_SOURCE, 10, MPI_COMM_WORLD, &status);
            for(int i = 0; i <= 2;i++){
            result = result + Matriz[recv+1][i] * Vetor[i];
            }
            MPI_Send(&result,1,MPI_INT,0,10,MPI_COMM_WORLD);
        }
    }

    MPI_Finalize();
}
