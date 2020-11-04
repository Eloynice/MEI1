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

    float result = 0.0;
    MPI_Status status;

    if (world_rank == 0){
        printf("\n");
        for(int i = world_rank+1; i < 20; i = i + world_size){
    		result += sqrt(i);
    	}
    	printf("My own: %f\n", result);
        float recv = 0.0;
        for (int i = 1; i < world_size; i++) { 
                MPI_Recv(&recv, 1, MPI_FLOAT, MPI_ANY_SOURCE, 10, MPI_COMM_WORLD, &status);
                printf("received: %f\n", recv);
                result = result + recv;
        } 

        printf("Result if: %f", result);
    }
    else{
    	for(int i = world_rank+1; i < 20; i = i + world_size){
    		result += sqrt(i);
    	}
    	MPI_Send(&result,1,MPI_FLOAT,0,10,MPI_COMM_WORLD);
    }

    MPI_Finalize();
}
