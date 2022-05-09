# RuneTek 3

RuneTek 3 was around for ~4.5 years, from Dec 2003 - July 2008, when it was replaced with RuneTek 4.

This targets revision 225 specifically, dated May 18 2004. RuneScape launched on March 29, 2004. It's impossible to make a single all-encompassing "RT3" client refactor.

There were two notable engine upgrades between the start and end of this RT3 era:
1) in June 2004, the main_file_cache.dat format was introduced, along with model and animation changes.
2) in June 2006, the main_file_cache.dat2 format was introduced. At some point after this, XTEA was used to encrypt l_ (loc) map files, and MIDIs were replaced with a custom instrument system.

Both of these upgrades brought other internal changes along with them that I don't want to delve too much into here.

## Questions/Answers

Q: [Don't you already have a 225 client](https://github.com/Pazaz/RS2-225)? What's different about this vs. RS2-225?  
A: This will not expand on the client, renaming everything to create a deob map for it is the main goal.

Q: Is there any use for this?  
A: Mainly educational. I hope we see later revisions adopt a similar approach. There is zero risk for any refactoring regressions this way.

Q: Why 225?  
A: 2004 is an unexplored time period. Coincidentally this is the last revision before June 2004, when the cache format as you know it was introduced. It's an interesting peek into a simpler time.

## Running the client

`./gradlew run --args='10 0 highmem members'`

## Directory Structure

`client/` - client project  
`client/bytecode/` - bytecode-level view of original source  
`client/src/` - deobfuscated output, ready to run  

`deob-annotations/` - openrs2 lib that allows tracking original class/method/field names

`lib/` - (not runtime libraries) original client to place in `openrs2/nonfree/`  

`share/` - profile and deob map to place in `openrs2/share/deob`. Allows for deterministic deobfuscation/decompilation  

## Notes

* ConstantArgTransformer simplifies some unused branches and can reduce functionality if the client code is expanded on
* In order to use the deob map in openrs2, the gl/loader/signlink code has to be commented out there, and client must use the jar loader instead of pack200
* DeadClass is only around because the current openrs2 transformers don't remove it. It's unused.
* InterfaceComponent should just be "Component" but it interferes with java.awt.Component in the client class. This would be fine to remove, but the deob map brings it back when decompiling each time.
* IndexedFont should just be "Font," but like before, it interferes with java.awt.Font. IndexedFont is an okay name for it though.
* There are still some flow obstructors that need to be removed manually (for now). I'll keep them in the source renamed until a transformer can pick up on them.
