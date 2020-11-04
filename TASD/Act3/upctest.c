#include <upc.h>
#include <stdio.h>

int main() {
  printf("Hello from thread %d of %d\n", MYTHREAD, THREADS);

  return 0;
}