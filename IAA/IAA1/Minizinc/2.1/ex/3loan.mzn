%loan
var float: R = 250; %Quartely payments
var float: P = 1000; %Principal initially borrowed
var 0.0..10.0: I = 0.04; %Interest rate

%intermediate values
var float: B1;
var float: B2;
var float: B3;
var float: B4;

constraint B1 = P * (1.0 + I) - R;
constraint B2 = B1 * (1.0 + I) - R;
constraint B3 = B2 * (1.0 + I) - R;
constraint B4 = B3 * (1.0 + I) - R;

solve satisfy;
output [
 "Borrowing ", show_float(0, 2, P), " at ", show(I*100.0), 
 "% interest, and repaying ", show_float(0, 2, R), 
  "\nper quarter for 1 year leaves ", show_float(0, 2, B4), " owing\n"
];