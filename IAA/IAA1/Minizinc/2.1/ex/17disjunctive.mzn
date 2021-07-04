include "disjunctive.mzn";
int: jobs = 5;
set of int: JOB = 1..jobs;
int: task = 5;
set of int: TASK = 1..task;
array [JOB,TASK] of int: d = 
     [| 1, 4, 5, 3, 6 
     | 3, 2, 7, 1, 2
     | 4, 4, 4, 4, 4  
     | 1, 1, 1, 6, 8
     | 7, 3, 2, 2, 1 |];      
int: total = sum(i in JOB, j in TASK)(d[i,j]);
int: digs = ceil(log(10.0,total));
array[JOB, TASK] of var 0..total: s;
var 0..total: end;

constraint %% ensure the tasks occur in sequence
    forall(i in JOB) (
      forall(j in 1..task-1)
           (s[i,j] + d[i,j] <= s[i,j+1]) /\
        s[i,task] + d[i,task] <= end);
constraint % ensure no overlap
  forall(j in TASK) (
      disjunctive([s[i,j] | i in JOB], [d[i,j] | i in JOB])
      );
solve minimize end;

output ["end = \(end)\n"] ++
       [show_int(digs,s[i,j]) ++ " " ++
        if j == task then "\n" else "" endif |
        i in JOB, j in TASK];