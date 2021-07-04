import csv, sqlite3

con = sqlite3.connect("database2.db")
cur = con.cursor()

	
fin = open("i.csv", "rt")
for line in fin:
	if '_' in line:
		ins = "'" + line
		ins = ins.replace('\n', '\'')
		cur.execute("SELECT internal FROM ug_ids WHERE external LIKE "+ins+";");
		ugid = cur.fetchall()
		print("% s" % ugid[0] + ",")
	elif '-1' in line:
		print("-1,")
	else:
		cur.execute("SELECT internal FROM ug_ids WHERE external = "+line+";")
		ugid = cur.fetchall()
		print("% s" % ugid[0] + ",")



con.commit()
con.close()
fin.close()
