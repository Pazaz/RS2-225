# Refactoring and Expansion of the Revision 225 RS2 client

Original client release: May 18, 2004

Huge thanks to Dane for his work on the 186 and 317 clients:
- https://github.com/thedaneeffect/RuneScape-Beta-Public
- https://github.com/thedaneeffect/RuneScape-317

Without him this would have taken much, much longer.  
A little credit goes to Major for a few variable names as well.

## Status

There's 4 steps to this project:
1) Rename (first pass): rename all classes, fields, variables, and clean up obfuscation techniques. Replace some magic numbers with variables. Code flow remains largely unchanged
2) Rename (second pass): reiterate through previous changes, rename anything that isn't consistent
3) Refactor: Rewrite code flow to make things easier to understand and build on.
4) Document: Describe every method and how their inputs/outputs work. Replace remaining magic numbers with clearly described variables.

After step 2, I do plan to deviate and add my own improvements.

Step 1 progress:
- Every class has been given a new name.
- Nearly all classes have had their methods and fields renamed.
- Most functions have been cleaned up.

## Notes

I have a simpler history here, with no refactoring (just renaming classes), https://github.com/Pazaz/RS2-225/tree/deob  
This branch will hopefully make things easier for people to follow, and maybe someone can see how refactoring takes place.
