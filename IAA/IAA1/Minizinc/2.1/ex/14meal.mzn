% Planning a balanced meal
include "table.mzn";
int: min_energy;
int: min_protein;
int: max_salt;
int: max_fat;
set of FOODS: desserts;
set of FOODS: mains;
set of FOODS: sides;
enum FEATURE = { name, energy, protein, salt, fat, cost}; 
enum FOODS; 
array[FOODS,FEATURE] of int: dd; % food database

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
constraint main[energy] + side[energy] + dessert[energy] >=min_energy;
constraint main[protein]+side[protein]+dessert[protein] >=min_protein;
constraint main[salt] + side[salt] + dessert[salt] <= max_salt;
constraint main[fat] + side[fat] + dessert[fat] <= max_fat;
constraint budget = main[cost] + side[cost] + dessert[cost];

solve minimize budget; 

output ["main = " ++ show(to_enum(FOODS,main[name]))++", " 
        ++ "side = " ++ show(to_enum(FOODS,side[name])) ++
        ", " ++ "dessert = "++show(to_enum(FOODS,dessert[name]))++
        ", "++ "cost = " ++ show(budget) ++ "\n"];