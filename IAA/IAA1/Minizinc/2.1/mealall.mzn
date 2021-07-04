% Planning a balanced meal
include "table.mzn";
enum FOOD = { icecream, banana, chocolatecake, lasagna, 
          steak, rice, chips, brocolli, beans} ;
set of FOOD: desserts = { icecream, banana, chocolatecake };
set of FOOD: mains = { lasagna, steak, rice };
set of FOOD: sides = { chips, brocolli, beans };

enum FEATURE = { name, energy, protein, salt, fat, cost };

array[FOOD, FEATURE] of int: dd = %food database
     [| icecream,      1200,  50,  10, 120,  400     % icecream
      | banana,         800, 120,   5,  20,  120     % banana
      | chocolatecake, 2500, 400,  20, 100,  600     % chocolate cake
      | lasagna,       3000, 200, 100, 250,  450     % lasagna
      | steak,         1800, 800,  50, 100, 1200     % steak
      | rice,          1200,  50,   5,  20,  100     % rice
      | chips,         2000,  50, 200, 200,  250     % chips
      | brocolli,       700, 100,  10,  10,  125     % brocolli
      | beans,         1900, 250,  60,  90,  150 |]; % beans
      
int: min_energy = 3300;
int: min_protein = 500;
int: max_salt = 180;
int: max_fat = 320;

array[FEATURE] of var int: main;
array[FEATURE] of var int: side;
array[FEATURE] of var int: dessert;
var int: budget;

constraint main[name] in mains;
constraint side[name] in sides;
constraint dessert[name] in desserts;
constraint table(main, dd);
constraint table(side, dd);
constraint table(dessert, dd);
constraint main[energy] + side[energy] + dessert[energy] >= min_energy;
constraint main[protein] + side[protein] + dessert[protein] >= min_protein;
constraint main[salt] + side[salt] + dessert[salt] <= max_salt;
constraint main[fat] + side[fat] + dessert[fat] <= max_fat;
constraint budget = main[cost] + side[cost] + dessert[cost];

solve minimize budget;

output ["main = ",show(to_enum(FOOD,main[name])),","
        , "side = ",show(to_enum(FOOD,side[name])),","
        , "dessert = ",show(to_enum(FOOD,dessert[name])),","
        , "cost = ",show(budget), "\n"];