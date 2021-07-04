include "alldifferent.mzn";

%Parameter

int: N; %Size of Costa array

%Decision variables

array[1..N] of var 1..N: CostasArray;  % implicit constraint of range
array[1..N, 1..N] of var int: DifTri;

%Constraints

constraint alldifferent(CostasArray); % so no rows have more than 1 mark

constraint forall(i, j in 1..N)(if i < j then DifTri[i,j] = CostasArray[j] - CostasArray[j-i] else DifTri[i,j] = 0  endif); % building the triangle

constraint forall(i in 1..N-1)(alldifferent([DifTri[i,j] | j in 1..N where i < j])); %Rows in difference triangle must be diferent
	
solve satisfy;

output [show(CostasArray)];



  
