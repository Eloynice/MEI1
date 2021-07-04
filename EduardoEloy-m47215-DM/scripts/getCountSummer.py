import csv
import math

months = ["jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"]
days = ["mon","tue","wed","thu","fri","sat","sun"]

fw = open("csvs/summerCount.txt","w+")

toWrite = ''
FFMC = 0
DMC = 0
DC = 0
ISI = 0
temp = 0
rh = 0
wind = 0
rain = 0
area = 0
count = 0
f = open("forestfires.csv", "r")
lines = f.readlines() 
for line in lines:
	split = line.split(",")
	if(split[2] == "aug" or split[2] == "jun" or split[2] == "jul" or split[2] == "sep"):
		fw.write(str(float(split[12]))+"\n")
fw.close()


f = open("csvs/summerCount.txt","r")
lines = f.readlines()
n0 = 0
n1 = 0
n50 = 0
n100 = 0
n150 = 0
n200 = 0
n250 = 0
n300 = 0
n350 = 0
n400 = 0
n450 = 0
n500 = 0
n550 = 0
n600 = 0
n650 = 0
n700 = 0
n750 = 0
n800 = 0
n850 = 0
n900 = 0
n950 = 0
n1000 = 0
aS1 = 0
aS50 = 0
aS100 = 0
aS150 = 0
aS200 = 0
aS250 = 0
aS300 = 0
aS350 = 0
aS400 = 0
aS450 = 0
aS500 = 0
aS550 = 0
aS600 = 0
aS650 = 0
aS700 = 0
aS750 = 0
aS800 = 0
aS850 = 0
aS900 = 0
aS950 = 0
aS1000 = 0

for line in lines:
	if(float(line[:-1]) == 0.0):
		n0 = n0 + 1
	else:
		val  = math.ceil(float(line[:-1]))
		if(val >= 1 and val < 50):
			n1 = n1 + 1
			aS1 = aS1 + float(line[:-1])
		elif(val >= 50 and val < 100):
			n50 = n50 + 1
			aS50 = aS50 + float(line[:-1])
		elif(val >= 100 and val < 150):
			n100 = n100 +  1
			aS100 = aS100 + float(line[:-1])
		elif(val >= 150 and val < 200):
			n150 = n150 +  1
			aS150 = aS150 + float(line[:-1])
		elif(val >= 200 and val < 250):
			n200 = n200 +  1
			aS200 = aS200 + float(line[:-1])
		elif(val >= 250 and val < 300):
			n250 = n250 +  1
			aS250 = aS250 + float(line[:-1])
		elif(val >= 300 and val < 350):
			n300 = n300 +  1
			aS300 = aS300 + float(line[:-1])
		elif(val >= 350 and val < 400):
			n350 = n350 +  1
			aS350 = aS350 + float(line[:-1])
		elif(val >= 400 and val < 450):
			n400 = n400 +  1
			aS400 = aS400 + float(line[:-1])
		elif(val >= 450 and val < 500):
			n450 = n450 +  1
			aS450 = aS450 + float(line[:-1])
		elif(val >= 500 and val < 550):
			n500 = n500 +  1
			aS500 = aS500 + float(line[:-1])
		elif(val >= 550 and val < 600):
			n550 = n550 +  1
			aS550 = aS550 + float(line[:-1])
		elif(val >= 600 and val < 650):
			n650 = n650 +  1
			aS650 = aS650 + float(line[:-1])
		elif(val >= 650 and val < 700):
			n650 = n650 +  1
			aS650 = aS650 + float(line[:-1])
		elif(val >= 700 and val < 750):
			n700 = n700 +  1
			aS700 = aS700 + float(line[:-1])						
		elif(val >= 750 and val < 800):
			n750 = n750 +  1
			aS750 = aS750 + float(line[:-1])
		elif(val >= 800 and val < 850):
			n800 = n800 +  1
			aS800 = aS800 + float(line[:-1])
		elif(val >= 850 and val < 900):
			n850 = n850 +  1
			aS850 = aS850 + float(line[:-1])
		elif(val >= 900 and val < 950):
			n900 = n900 +  1
			aS900 = aS900 + float(line[:-1])
		elif(val >= 950 and val < 1000):
			n950 = n950 +  1
			aS950 = aS950 + float(line[:-1])
		elif(val >= 1000):
			n1000 = n1000 +  1
			aS1000 = aS1000 + float(line[:-1])
print(n0)
print(n1)
print(n50)
print(n100)	
print(n150)	
print(n200)	
print(n250)	
print(n300)	
print(n350)	
print(n400)
print(n450)
print(n500)
print(n550)
print(n600)
print(n650)
print(n700)
print(n750)
print(n800)
print(n850)
print(n900)
print(n950)
print(n1000)



