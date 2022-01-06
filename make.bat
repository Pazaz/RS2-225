@echo off
rmdir /S /Q bin
mkdir bin
javac -d bin -cp src src/com/runescape/client.java
java -cp bin com.runescape.client 10 0 highmem members
