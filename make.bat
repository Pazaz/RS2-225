@echo off
mkdir bin
javac -d bin -cp src src/client.java
java -cp bin client 10 0 highmem members
