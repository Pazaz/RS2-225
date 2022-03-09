@echo off
mvn package && java -jar RuneScape/target/runescape-1.0.jar %1 %2
