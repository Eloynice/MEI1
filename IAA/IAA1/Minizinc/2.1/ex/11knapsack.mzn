%knapsack
enum ITEM;
int: capacity;

array[ITEM] of int: weight;
array[ITEM] of int: profit;

var set of ITEM: knapsack;

constraint sum (i in knapsack) (weight[i] <= capacity);

solve maximize sum (i in knapsack) (profit[i]);

output ["knapsack = \(knapsack)\n"];