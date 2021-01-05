import csv, re, sqlite3

con = sqlite3.connect("database2.db")
cur = con.cursor()
f = open("graph_init", "w")
f2 = open("area_init", "w")


toWrite = ''


for i in range(1, 1374):
	cur.execute("Select ug2 from adj where ug1 like "+str(i))
	result = cur.fetchall()
	if(result == []):
		toWrite = "0\n"
	else:
		for j in range(0, len(result)):	
			toWrite += str(result[j]).replace(')','').replace('(','')
		toWrite = toWrite[:-1]+"\n"
	f.write(toWrite)
	
	cur.execute("SELECT area FROM area where ug like "+str(i)) 
	result = cur.fetchall()
	toWrite = str(result[0]).replace(')','').replace('(','')
	toWrite = toWrite[:-1]+"\n"
	f2.write(toWrite)
	toWrite = ''
