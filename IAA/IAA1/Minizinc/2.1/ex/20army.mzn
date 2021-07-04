% army
int: budget = 10000;
var 1..150: J;
var 1..1000: F;
var 1..400: L;
var 1..500: Z;
var int: power;
constraint 13*F + 21*L + 17*Z + 100*J <= 10000;
constraint power = F*6 + L*10 + Z*8 + J*40;
solve maximize power;

output ["Number of soldiers: \n" ++ "Feng = \(F)\n" ++
        "Liu = \(L)\n" ++ "Zhao = \(Z)\n" ++ "Jan = \(J)\n" ++
        "TOTAL POWER = \(power)"];