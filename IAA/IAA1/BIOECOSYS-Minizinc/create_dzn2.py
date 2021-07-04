import csv, re, sqlite3

con = sqlite3.connect("database2.db")
cur = con.cursor()
HASCUT = "hascut = array3d(1..1373,1..75,1..10,[ "




for i in range(1, 1374):
	cur.execute("SELECT presc, period FROM hascut where ug like "+str(i))
	result = cur.fetchall()
	l = len(result)
	rr = 1
	if(result != []):
		current = result[0][0]
		HASCUT = HASCUT + str(current)+","
		c = 0
		for p in range(0, l):
			if(result[p][0] != current):
				current = result[p][0]
				for o in range(c,9):
					HASCUT = HASCUT + "0,"
				rr = rr+1
				HASCUT = HASCUT + " " + str(current)+","
				c = 0
			c = c+1
			HASCUT = HASCUT + str(result[p][1])+","
			
		for u in range(c, 9):
			HASCUT = HASCUT + "0,"
		HASCUT = HASCUT + " "
		
		cur.execute("Select count(distinct presc) from action where ug like "+str(i)+" group by ug");
		l = int(cur.fetchone()[0]) 
			
		for p in range(rr, 75):
			HASCUT = HASCUT + "0,"
			for k in range(0, 9):
				HASCUT = HASCUT + "0,"
			HASCUT = HASCUT + " "
		HASCUT = HASCUT + "\n"
	
	else:
		for p in range(1, 76):
			HASCUT = HASCUT + "0,"
			for k in range(0, 9):
				HASCUT = HASCUT + "0,"
			HASCUT = HASCUT + " "
		HASCUT = HASCUT + "\n"
		


HASCUT = HASCUT[:-3]
HASCUT = HASCUT + "]);"

print(HASCUT)
