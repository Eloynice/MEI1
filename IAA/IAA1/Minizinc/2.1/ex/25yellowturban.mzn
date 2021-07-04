enum MOVES;
int: timeBound;
array[MOVES] of int: power;
array[MOVES] of int: duration;

timeBound = 10;
power = [6, 8, 5, 3, 4];
duration = [4, 5, 3, 2, 3];

var set of MOVES: occur;

constraint sum(i in occur)(duration[i]) <= timeBound;
solve maximize sum(i in occur)(power[i]);