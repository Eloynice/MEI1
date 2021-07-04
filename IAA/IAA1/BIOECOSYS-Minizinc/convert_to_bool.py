import csv, re

fin = open("iii.csv", "rt")
for line in fin:
	s = line.split(",")
	if(s[3] != "0"):
		print(s[0]+","+s[1]+","+s[6])
	
fin.close()
