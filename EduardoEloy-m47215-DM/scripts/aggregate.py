import csv

months = ["jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"]
days = ["mon","tue","wed","thu","fri","sat","sun"]

fw1 = open("csvs/forestfiresAGMean.csv","w+")
fw2 = open("csvs/forestfiresAGNumber.csv","w+")
fw3 = open("csvs/forestfiresAGBurned.csv","w+")

fw1.write("month,day,FFMC,DMC,DC,ISI,temp,RH,wind,rain,meanArea\n")
fw2.write("month,day,FFMC,DMC,DC,ISI,temp,RH,wind,rain,numberOfFires\n")
fw3.write("month,day,FFMC,DMC,DC,ISI,temp,RH,wind,rain,totalAreaBurned\n")

for m in range(0, len(months)):
	for d in range(0,len(days)):
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
			if(split[0] != 'X'):
				if(split[2] == months[m] and split[3] == days[d]):
					FFMC += float(split[4])
					DMC += float(split[5])
					DC += float(split[6])
					ISI += float(split[7])
					temp += float(split[8])
					rh += float(split[9])
					wind += float(split[10])
					rain += float(split[11])
					area += float(split[12])
					count += 1
		if(count > 0):
			toWrite += months[m]+","+days[d]+","
			toWrite += str(round(FFMC/count,2))+","+str(round(DMC/count,2))+","
			toWrite += str(round(DC/count,2))+","+str(round(ISI/count,2))+","
			toWrite += str(round(temp/count,2))+","+str(round(rh/count,2))+","
			toWrite += str(round(wind/count,2))+","+str(round(rain/count,2))+","
			fw1.write(toWrite+str(round(area/count,2))+"\n")
			fw2.write(toWrite+str(count)+"\n")
			fw3.write(toWrite+str(round(area,2))+"\n")
		
