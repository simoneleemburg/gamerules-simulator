# This is the gamerules simulator! #

The gamerules simulator is there to automatically play out combat scenarios under roleplaying game rules and analyse how these turn out.

The idea is that you should be able to play out scenarios and run them a number of times and have the results analysed and compared for you. 
Allowing you to see the balance implications.

"O if I add this house-rule, Chuck with his longsword will almost always beat Dave!"

Game rules and mechanics can be customized, as well as how dice are rolled and which character builds to use. 

This focuses currently on supporting good old DND 3.5 rule (see: https://www.d20srd.org/index.htm), with some home-brew variation rules. 
Although a highly pluggable setup is envisioned, allowing maybe in the future to use D10 or other systems on the same simulation engine.

## Getting started ##

Make sure to install:
 - Scala
 - sbt
 
Build: `sbt test`
Run with sbt: `sbt run`
Or run using the App file `nl.sass.gamesimulator.Simulator` directly through your favorite IDE.

Currently functionality is limited to run the App and see some basic output printed in your console.
You can edit the App to tweak dice rolls and characters used and such.