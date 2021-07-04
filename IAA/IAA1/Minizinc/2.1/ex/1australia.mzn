% Australia 
% minizinc --solver gecode -D"COLOR = { red, yellow, blue };" 1australia.mzn
enum COLOR = {Blue, Red, Yellow};

var COLOR: wa;
var COLOR: nt;
var COLOR: sa;
var COLOR: q;
var COLOR: nsw;
var COLOR: v;
var COLOR: t;

constraint wa != nt /\ wa != sa /\ nt != sa /\ nt != q /\ sa != q /\ sa != nsw /\
           sa != v /\ nsw != q /\ nsw != v;
           
 solve satisfy;


/*
int: nc = 3;
var 1..nc: wa;
var 1..nc: nt;
var 1..nc: sa;
var 1..nc: q;
var 1..nc: nsw;
var 1..nc: v;
var 1..nc: t;

constraint wa != nt;
constraint wa != sa;
constraint nt != q;
constraint nt != sa;
constraint sa != q;
constraint sa != nsw;
constraint sa != v;
constraint nsw != q;
constraint nsw != v;
solve satisfy;
output ["wa=\(wa)\t nt=\(nt)\t sa=\(sa)\n",
        "q=\(q)\t nsw=\(nsw)\t v=\(v)\n",
         "t=", show(t),  "\n"];
*/