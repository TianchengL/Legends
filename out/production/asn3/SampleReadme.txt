# CS611-Legends

Name
-------------------------------------------------------------------------------------------------
--Tiancheng Liang--
--U39385958--


Files
-------------------------------------------------------------------------------------------------
<.java file> - <1 line comment about the file 
<Armor.java> - <Armor class represent each armor item,implements Equitable interface to indicate if this armor has been equipped>
<Board.java> - <Board object used to init bunch of cells, print cells on command line>
<Castable.java> - <castable interface for spell cast, needs to be implemented by each spell concrete class>
<Cell.java> - < abstract class for each cell, inaccessible, marketCell and common cell needs to extend this class>
<Character.java> - < an abstract class for hero and monster or any other character in game>
<CharacterFactory.java> - <return a list of hero or monster contain with singleton pattern>
<CommonCell.java> - <common cell class extend cell>
<DexterityAndAgility.java> - <concrete class for Skill which could be injected to needed classes as strategy pattern. Skill favored on dexterity and agility>
<StrengthAndAgility.java> - <needs to init by hero who favored skill are strength and agility>
<StrengthAndDexterity.java> - <needs to init by hero who favored skill are strength and dexterity>
<Dragon.java> - <concrete class for dragon extend monster class>
<Equipable.java> - <interface that needs to be implemented by any item that could be equipped>
.........etc.
All class description is in the start of each class.


Notes:
-------------------------------------------------------------------------------------------------
1. Files to be parsed should be stored in ConfigFiles, for parser class to read class
    Please put ConfigFiles under the src directory
2. Bonus Done
	The board has color. 

3. Things instructions to note
	Design Pattern that I used:
		1): Strategy pattern for hero's skill. Each hero has different skill. Abstract class hero should implement skill interface. 
		2): Singleton pattern and Factory pattern. Once object has concrete object, it will be returned. (In ItemFactory. java and CharacterFactory.java)

How to run:
-------------------------------------------------------------------------------------------------
1. Navigate to the directory after downloading the project

	After downloading the project, find the Main class that is where main method located. And execute that class.

2. Run the following instructions on command line:
	javac *.java
	java Main.java
