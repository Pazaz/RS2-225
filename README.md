# RS2-225

This is an offline version of the client that hosts a small internal server to allow you to run around and listen to music.  
[Go check out this branch for my latest work, just note it does not run a local server.](https://github.com/Pazaz/RS2-225/tree/main)

## Questions / Answers

Q: How do I jump in and play around with things?  
A: Clone the repo and run `./gradlew client:run --args='server'`. You can log in and run around, explore to your heart's desire (music included!). When I make jars to distribute I change `rs2.client.startServer` to `true` and build, so it works standalone/offline.

## Server Commands

* `::tele` (teleport to specific coordinates)
* `::region` (teleport to the center of a region)
* `::pos` (get pos info)
* `::over` (go back above aka teleport 6400 down)
* `::under` (go underground aka teleport 6400 up)
* `::home` (go to lumbridge)
* `::up` (go up 1 level)
* `::down` (go down 1 level)

## Using .gitmeta

This is used to preserve file metadata e.g. timestamps, and is really only used for the cache file dates. It's not necessary to run or build anything here.

Download the [git-meta](https://github.com/renard/git-meta) script and run: `git meta set`
