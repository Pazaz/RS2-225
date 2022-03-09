# Refactoring and Expansion of the Revision 225 RS2 client

Original client release: May 18, 2004

## Usage

1) Clone this repo.
2) Run `mvn package`
3) The client jar should be placed in `RuneScape/target/runescape-1.0.jar`

There are some additional sub-projects in this repo that have their own jars as well.
The engine, RuneTek3, is a depedency and does not have a jar created. Its classes get copied to each project's jar.

## Status

There's 4 steps to this project:
1) Rename (first pass): rename all classes, fields, variables, and clean up obfuscation techniques. Replace some magic numbers with variables. Code flow remains largely unchanged
2) Rename (second pass): reiterate through previous changes, rename anything that isn't consistent
3) Refactor: Rewrite code flow to make things easier to understand and build on.
4) Document: Describe every method and how their inputs/outputs work. Replace remaining magic numbers with clearly described variables.

Step 1 is complete, and I'm working on reiterating through everything to catch what I've missed.

At this point the engine can be used freely for non-client projects.  
For example: I've made a headless discord bot that renders to PNG/GIF.

## Notes

I have a simpler history here, with no refactoring (just renaming classes), https://github.com/Pazaz/RS2-225/tree/deob  
This branch will hopefully make things easier for people to follow, and maybe someone can see how refactoring takes place.

## Credits

Huge thanks to:

1) Dane for his work on the 186 and 317 clients:
- https://github.com/thedaneeffect/RuneScape-Beta-Public
- https://github.com/thedaneeffect/RuneScape-317

2) Major for his work on the 317 client:
- https://github.com/Rune-Status/Major--Renamed-317

3) jameskmonger for his work on the 317 client:
- https://github.com/Jameskmonger/317refactor

Without them this would have taken much, much longer.  

