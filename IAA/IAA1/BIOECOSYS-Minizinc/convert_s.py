import csv, re

fin = open("ii.csv", "rt")
for line in fin:
	s = line.split(",")
	if(float(s[0]) > 0):
		t = "1"
	else:
		t = "0"
	if(float(s[1]) > 0):
		h = "1"
	else:
		h = "0"
	if(float(s[2]) > 0):
		c = "1"
	else:
		c = "0"
	print(t+","+h+","+c+","+s[3]+","+s[4].replace('\n',''))
	
fin.close()
