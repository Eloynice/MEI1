import csv, re, sqlite3

con = sqlite3.connect("database2.db")
cur = con.cursor()
f = open("ugs_init", "w")

toWrite = ''

for i in range(1, 1374):
	cur.execute("SELECT distinct presc FROM action where ug like "+str(i)) 
	result = cur.fetchall()
	if(result == []):
		toWrite = "0\n"
	else:
		for j in range(0, len(result)):	
			toWrite += str(result[j]).replace(')','').replace('(','')
		toWrite = toWrite[:-1]+"\n"
	f.write(toWrite)
	toWrite = ''

