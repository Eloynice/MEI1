import csv, re, sqlite3

fin = open("ii.csv", "rt")

con = sqlite3.connect("database2.db")
cur = con.cursor()

first_line = fin.readline()
s = first_line.split(",")
current = s[0]
cur.execute("SELECT area FROM area where ug like "+current) 
result = cur.fetchone() 
area = float(result[0])
totarea = area
for line in fin:
	s = line.split(",")
	if(s[0] == current):
		totarea = totarea + float(s[2]) - area
	else: 
		print(str(current)+","+str(totarea))
		if(str(s[1]) == "-1"):
			current = s[0]
			cur.execute("SELECT area FROM area where ug like "+current) 
			result = cur.fetchone() 
			area = float(result[0])
			totarea = area
		else:
			current = s[0]
			cur.execute("SELECT area FROM area where ug like "+current) 
			result = cur.fetchone() 
			area = float(result[0])
			totarea = float(s[2]) 
			
print(str(current)+","+str(totarea))

fin.close()

