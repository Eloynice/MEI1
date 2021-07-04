set of int: UG = 1..1373;
set of int: PRESCS = 0..628;
array[UG] of var 0..628: ugs;

array[UG, 1..75] of int: ugs_presc;
array[1..1373] of int: lengths;
array[1..1373] of float: area;
array[1..1373] of float: adj_area;
array[UG, 1..75,1..10] of 0..628: hascut;
array[UG, 1..20] of int: adj;
array[1..1373] of int: adj_l;



function var float: getAreaCut(int: ug, int: period, float:cut) = cut + sum(viz in 1..adj_l[ug])(
sum(j in 1..75)(if(hascut[adj[ug,viz], j, 1] == ugs[adj[ug,viz]]) then
sum(k in 2..10)(if(hascut[adj[ug,viz], j, k] == period) then area[adj[ug,viz]] else 0 endif) else 0 endif));




predicate recall(int: i, int: presc_i, int: len) =
        if(presc_i == len) then ugs[i] = ugs_presc[i,presc_i] else
        ugs[i] = ugs_presc[i, presc_i] \/ recall(i, presc_i + 1, len) endif;

constraint forall(i in UG)( if(lengths[i] == 0) then ugs[i] = 0 else
        ugs[i] = ugs_presc[i,1] \/ recall(i, 1,lengths[i]) endif);

constraint forall(i in UG)(forall(j in 1..75)(if(hascut[i,j,1] == ugs[i]) then forall(k in 2..10)(getAreaCut(i, hascut[i,j,k], area[i]) <= 500) endif));       
                
                                

solve satisfy;


output  [ "UG_\(i) = \(ugs[i]) \n"|
         i in UG];