import csv, re, sqlite3

con = sqlite3.connect("database2.db")
cur = con.cursor()
DZN = "ugs_presc = [| "
LENS = "lengths = ["
AREA = "area = ["
ADJ = "adj_area = ["
ADJ_UG = "adj = [| "
ADJ_L = "adj_l = [ "

for i in range(1, 1374):
	cur.execute("SELECT distinct presc FROM action where ug like "+str(i)) 
	result = cur.fetchall()
	if(result == []):
		for k in range(0, 75):
				DZN = DZN + "0,"
		DZN = DZN + " |\n"
		LENS = LENS + "0,"
	else:
		for j in range(0, len(result)):
			DZN = DZN + str(result[j])
		if(len(result) != 75):
			for k in range(len(result), 75):
				DZN = DZN + "0),"	
		DZN = DZN[:-2] + " |\n"
		LENS = LENS + str(len(result))+","
		
	cur.execute("SELECT area FROM area where ug like "+str(i)) 
	result = cur.fetchall()
	AREA = AREA + str(result[0])
	
	cur.execute("SELECT adj_area FROM adj_area where ug_id like "+str(i)) 
	result = cur.fetchall()
	ADJ = ADJ + str(result[0])
	
	cur.execute("Select ug2 from adj where ug1 like "+str(i))
	result = cur.fetchall()
	l = len(result)
	for j in range(0, len(result)):
		ADJ_UG = ADJ_UG + str(result[j])	
	for j in range(len(result), 20):
		ADJ_UG = ADJ_UG + "0),"	
	
	ADJ_UG = ADJ_UG[:-2] + " |\n"
	ADJ_L = ADJ_L + str(len(result))+","

	
	



DZN = DZN[:-1]
DZN = DZN + "];"
print(DZN)

print("\n")

LENS = LENS[:-1]
LENS = LENS + " ];"
print(LENS)

print("\n")

AREA = AREA[:-2]
AREA = AREA + " ];"
print(AREA)

print("\n")

ADJ = ADJ[:-2]
ADJ = ADJ + " ];"
print(ADJ)

print("\n")

ADJ_UG = ADJ_UG[:-1]
ADJ_UG = ADJ_UG + "];"
print(ADJ_UG)

print("\n")

ADJ_L = ADJ_L[:-1]
ADJ_L = ADJ_L + "];"
print(ADJ_L)



