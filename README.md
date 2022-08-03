# Farmland-Tool
ReadMe for FarmLand Tool Program

 
## To run this program

1- Download the following source files into your workspace:

> [prompt %] ls
> Coordinate.java		FarmLand.java		FarmLandTool.java

2- Compile all source files:

> [prompt %] javac *.java 

3- Run the program:
> [prompt %] java FarmLandTool


## To use the program

You will initially be presented with the following prompt:
```
Welcome to the Target Farm Land Analyzer!

Please do one of the following:
Enter [1] to run sample test - Large Box
Enter [2] to run sample test - Tic-Tac-Toe
Enter [3] to run an out of bounds test
Enter custom input:
```

You have 4 options to choose from.

* Hit 1 and Enter for Option 1, which will run the sample data 1 and compare the output to the expected results.

* Hit 2 and Enter for Option 2, which will run the sample data 2 and compare the output to the expected results.

* Hit 3 and Enter for Option 3 , which will run a test with barren land that is off the grid. An exception is expected here.

* For the last option, you can enter your own custom data in the expected format. If the data is not in the expected format, you will get an error

```
Format: {"A1 B1 C1 D1”, “A2 B2 C2 D2”, “An Bn Cn Dn”} 

A is the x coordinate of the bottom left of the Barren Rectangle
B is the y coordinate of the bottom left of the Barren Rectangle
C is the x coordinate of the top right of the Barren Rectangle
D is the y coordinate of the top right of the Barren Rectangle
```
## Examples
### Example of option 1:
```
[prompt %] java FarmLandTool 
Welcome to the Target Farm Land Analyzer!

Please do one of the following:
Enter [1] to run sample test - Large Box
Enter [2] to run sample test - Tic-Tac-Toe
Enter [3] to run an out of bounds test
Enter custom input:

1

Analyzing...
116800 116800
Test 1 passed!
```
### Example of option 2:
```
[prompt %] java FarmLandTool
Welcome to the Target Farm Land Analyzer!

Please do one of the following:
Enter [1] to run sample test - Large Box
Enter [2] to run sample test - Tic-Tac-Toe
Enter [3] to run an out of bounds test
Enter custom input:

2

Analyzing...
22816 192608
Test 2 passed!
```
### Example of option 3:
```
[prompt %] java FarmLandTool
Welcome to the Target Farm Land Analyzer!

Please do one of the following:
Enter [1] to run sample test - Large Box
Enter [2] to run sample test - Tic-Tac-Toe
Enter [3] to run an out of bounds test
Enter custom input:

3
Exception in thread "main" java.lang.IndexOutOfBoundsException: Barren Land falls out of bounds of the grid
	at FarmLand.setBarrenFarmLand(FarmLand.java:74)
	at FarmLandTool.main(FarmLandTool.java:85)
```
### Example of Custom Input
```
[prompt %] java FarmLandTool
Welcome to the Target Farm Land Analyzer!

Please do one of the following:
Enter [1] to run sample test - Large Box
Enter [2] to run sample test - Tic-Tac-Toe
Enter [3] to run an out of bounds test
Enter custom input:

{“48 192 351 207”, “48 392 351 407”}                                           
Analyzing...
230272
```
