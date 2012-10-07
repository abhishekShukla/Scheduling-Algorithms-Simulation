ABHISHEK SHUKLA RAVISHANKARA - 107598884

The following items have been submitted as a part of the assignment:

1. Source Files in folder src
	
	EdfScheduler.java
	LstScheduler.java
	RmsScheduler.java
	Tasks.java
	ReadInput.java

2. Three input files
	
	input1.txt
	input2.txt
	input3.txt
	
3. Three respective output files
	
	output1.txt
	output2.txt
	output3.txt
	
4. Ant build File

	build.xml
	
5. Report

6. Pre-compiled classes and jar file is submitted in the folder
	
	build/classes/*
	build/jar/Schedulers.jar

Pre-Requisites: 

1. Java installed
2. Ant installed
	
How to build the project (Assuming CWD to current working directiory):

1. Copy the src folder, build.xml and input files to CWD
2. Run command ant -f build.xml
	
	This will create a folder "build" in CWD
	Build folder will contain two folders
		
		1. classes: This folder will contain the classes
		2. jar: This folder will contain the jar file

3. cd build/jar

4. 5. To make it easy copy the input files to CWD/build/jar
 
5. The command to get the output files

	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "<absolute path to input file>" <rms or edf or lst or all> <simulation time> > <output file name>
	
	Examples: 
	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "input1.txt" rms 630 > output1.txt
	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "input2.txt" all 60 > output2.txt
	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "input3.txt" edf 40 > output3.txt
	
6. The output files for the following commands have been submitted

	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "input1.txt" all 630 > output1.txt
	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "input2.txt" all 60 > output2.txt
	java -cp Schedulers.jar edu.emsys.scheduling.ReadInput "input3.txt" all 20 > output3.txt
