@echo off
mkdir bin
javac -d bin -cp src src/com/runescape/Game.java
java -cp bin com.runescape.Game 10 0 highmem members
pause
