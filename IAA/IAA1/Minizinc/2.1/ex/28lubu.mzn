% Three heroes combating Lü Bu
include "alldifferent.mzn";

enum HERO;
enum SPOT;

array[HERO,SPOT] of int: damage;
array[HERO] of var SPOT: pos;

constraint forall(s in SPOT)(sum(h in HERO)(pos[h] = s) <= 1);
constraint forall(h1, h2 in HERO where h1 < h2)(pos[h1] != pos[h2]);
constraint alldifferent(pos);

var int: tDamages = sum(h in HERO)(damage[h,pos[h]]);
solve maximize tDamages;

output ["\(h): \(pos[h])\n" | h in HERO] ++ ["Total Damages: \(tDamages)"];