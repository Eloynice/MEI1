% Baking Cakes 
% minizinc 2cakes.mzn -D \ "flour=4000;banana=6;sugar=2000;butter=500;cocoa=500;"
var 0..100: b; %n of banana cakes
var 0..100: c; %n of choco cakes;
int: flour;
int: banana;
int: sugar;
int: butter;
int: cocoa;

constraint assert(flour >= 0,"Invalid datafile: " ++
                  "Amount of flour should be non-negative");
constraint assert(banana >= 0,"Invalid datafile: " ++
                  "Amount of banana should be non-negative");
constraint assert(sugar >= 0,"Invalid datafile: " ++
                  "Amount of sugar should be non-negative");
constraint assert(butter >= 0,"Invalid datafile: " ++
                  "Amount of butter should be non-negative");
constraint assert(cocoa >= 0,"Invalid datafile: " ++
                  "Amount of cocoa should be non-negative");


constraint b * 250 + c * 200 <= flour;
constraint b * 2 <= banana;
constraint c * 75 <= cocoa;
constraint b * 100 + c * 150 <= butter;
constraint b * 75 + c *150 <= sugar;

  var int: profit = b * 400 + c * 450;

solve maximize profit;
output ["b = \(b) " ++ "c = \(c)"];