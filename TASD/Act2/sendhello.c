#include <mpi.h>
#include <stdio.h>

int main(int argc, char** argv) {
    // Initialize the MPI environment
    MPI_Init(NULL, NULL);

    int world_size;
    MPI_Comm_size(MPI_COMM_WORLD, &world_size);

    int world_rank;
    MPI_Comm_rank(MPI_COMM_WORLD, &world_rank);

    char processor_name[MPI_MAX_PROCESSOR_NAME];
    int name_len;
    MPI_Get_processor_name(processor_name, &name_len);

    // Print off a hello world 
    char a[5];
    MPI_Status status;
    printf("world_rank: %d", world_rank);
    if (world_rank == 0){
        printf("\n");
        for (int i = 1; i < 4; i++) { 
                
                MPI_Recv(a, 5, MPI_CHAR, MPI_ANY_SOURCE, 10, MPI_COMM_WORLD,&status);
                printf("%s\n",a);
        } 
    }
    else{
        MPI_Send("HELLO",5,MPI_CHAR,0,10,MPI_COMM_WORLD);
    }

    MPI_Finalize();
}
