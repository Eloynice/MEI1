CP=.:lib/voce.jar:lib/cmulex.jar:lib/cmu_us_kal.jar:lib/en_us.jar:lib/freetts.jar:lib/jsapi.jar:lib/sphinx4.jar:lib/WSJ_8gau_13dCep_16k_40mel_130Hz_6800Hz.jar

all:
	javac -classpath $(CP) *java

run:
	java -classpath $(CP) synthesisTest

jogo:
	java -classpath $(CP) Jogo

clean:
	rm -f *class *~ 

