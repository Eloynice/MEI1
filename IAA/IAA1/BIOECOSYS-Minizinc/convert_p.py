import csv, sqlite3, re

con = sqlite3.connect("database2.db")
cur = con.cursor()

	
fin = open("i.csv", "rt")
for line in fin:
	
	if re.search('[a-zA-Z]', line):
		ins = "'" + line
		ins = ins.replace('\n', '\'')
		cur.execute("SELECT internal FROM presc_ids WHERE external LIKE "+ins+";");
	else:
		cur.execute("SELECT internal FROM presc_ids WHERE external = "+line+";")
	
	pid = cur.fetchall()
	print("% s" % pid[0] + ",")


con.commit()
con.close()
fin.close()
