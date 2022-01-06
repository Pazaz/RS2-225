# Refactoring and Expansion of the Revision 225 RS2 client

Original client release: May 18, 2004

Huge thanks to Dane for his work on the 186 and 317 clients:
- https://github.com/thedaneeffect/RuneScape-Beta-Public
- https://github.com/thedaneeffect/RuneScape-317

Without him this would have taken much, much longer.  
A little credit goes to Major for a few variable names as well.

## Status

- Every class has been given a name.
- Some classes are refactored (~60%)
  - com.runescape.cache.* has 9/13 done
  - com.runescape.graphics.* has 1/7 done
  - com.runescape.scene.* has 14/23 done
  - com.runescape.sound.* has 3/3 done
  - com.runescape.util.* has 10/15 done
  - com.runescape.* has 1/3 done
- No new features yet.

## Notes

I have a simpler history here, with no refactoring (just renaming classes), https://github.com/Pazaz/RS2-225/tree/deob  
This branch will hopefully make things easier for people to follow, and maybe someone can see how refactoring takes place.
