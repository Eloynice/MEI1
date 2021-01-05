import csv, re, sqlite3

con = sqlite3.connect("database2.db")
cur = con.cursor()
f3 = open("period_init", "w")


toWrite = ''


for i in range(1, 1374):
	cur.execute("SELECT period FROM hascut where ug like "+str(i))
	result = cur.fetchall()
	previous = 0
	c = 0
	for j in range(0,len(result)):
		
		if(int(str(result[j][0]).replace(',','')) < previous):
			for k in range(c, 9):
				toWrite += "0,"
			c = 1
			toWrite = toWrite[:-1]
			previous = int(str(result[j][0]).replace(',',''))
			toWrite += "/"
			toWrite += str(result[j][0])+","
		elif(int(str(result[j][0]).replace(',','')) == previous and previous != 0):
			for k in range(c, 9):
				toWrite += "0,"
			c = 1
			toWrite = toWrite[:-1]
			previous = int(str(result[j][0]).replace(',',''))
			toWrite += "/"
			toWrite += str(result[j][0])+","
		else:
			toWrite += str(result[j][0])+","
			previous = int(str(result[j][0]).replace(',',''))
			c = c + 1
	for k in range(c, 9):
		toWrite += "0,"
	toWrite = toWrite[:-1] + "\n"
	f3.write(toWrite)
	toWrite = ''
