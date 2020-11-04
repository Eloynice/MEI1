shared int all_hits[THREADS];

int main() {
	int i, hits = 0, tot = 0, trials = 1000000;
	srand(1+MYTHREAD*17);
	for (i = 0; i < trials; ++i)
		hits += trial_in_disk();
	all_hits[MYTHREAD] = hits;
	upc_barrier;
	if (MYTHREAD == 0) {
		for (i = 0; i < THREADS; ++i)
			tot += all_hits[i];
		printf("Pi approx %g\n", 4.0*tot/trials/THREADS);
	}
}