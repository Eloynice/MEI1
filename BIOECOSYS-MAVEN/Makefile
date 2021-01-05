all: make

make:
	python3 src/main/java/initialize_ugs.py
	python3 src/main/java/initialize_graph.py
	python3 src/main/java/initialize_period.py
	
test:
	javac -cp ".:./choco-solver-4.10.5-jar-with-dependencies.jar" src/ForestMan.java src/UgsInitializer.java 
	java -cp ".:./choco-solver-4.10.5-jar-with-dependencies.jar" src/ForestMan
	

clean:
	rm -rf *.txt

