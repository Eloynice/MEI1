include "alldifferent.mzn";

%Parameter

int: N; %Size of Costa array

%Decision variables

array[1..N] of var 1..N: CostasArray;  % implicit constraint of range
array[1..N, 1..N] of var int: DifTri;

%Constraints

constraint alldifferent(CostasArray); % so no rows have more than 1 mark

constraint forall(i, j in 1..N)(if i < j then DifTri[i,j] = CostasArray[j] - CostasArray[j-i] else DifTri[i,j] = 0  endif); % building the triangle

constraint forall(i in 1..N-1)(alldifferent([DifTri[i,j] | j in 1..N where i < j])); %Rows in difference triangle must ve diferent

%Redundant Constraints

constraint redundant_constraint(forall(i in 1..N)(CostasArray[i] <= N \/ CostasArray[i] > 0));%Values are below N and above 0, redundant because of line 7

constraint redundant_constraint(forall(i,j in 1..N)(if(i < j) then CostasArray[i] != CostasArray[j] endif));%All values are different, redundant because of line 11

constraint redundant_constraint(forall(i,j in 1..N)(if (i < j) then DifTri[i,j] != 0 endif));%All values in CostasArray are different but done through DifTri, redundant because of line 11
	
constraint symmetry_breaking_constraint(if N != 1 then CostasArray[1] < CostasArray[N] endif);%If we constrain the value in the first index to be lower than its "inverse" index, the last one, we remove from the solution pool symmetric solutions.
	
solve :: int_search(CostasArray, input_order, indomain_min)
satisfy;

output [show(CostasArray)];



  
