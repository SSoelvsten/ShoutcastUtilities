# Shoutcast Utilities
These are a collection of programs I've made to make the running of a stream a little less cumbersome. Currently it only consists of two program.

## Global shortcuts
By using JNativeHook a keylogger is implemented, which reacts to certain key-combinations. This way you can interact with the software without having to actually defocus the game!

The keybinds are rebindable, though you have to use the codes you can find in "keyReference.txt". Rebindings can be done in "config.cfg". The following are the standard bindings

- ALT GR: Universal meta-key as identifier. This key has to be pressed for the others to work.
- Numpad 0: Commit the current values in ALL of the programs linked
- Numpad 7: Team Score Manager: Increase score for Team A
- Numpad 4: Team Score Manager: Decrease score for Team A
- Numpad 9: Team Score Manager: Increase score for Team B
- Numpad 6: Team Score Manager: Decrease score for Team B
- Numpad 8: Team Score Manager: Swap teams

## Team Score .txt Manager
A small java program to manage the .txt files used for the text on an overlay during a shoutcast for both teams. This includes full name of the teams, their tags and the current score.
These can be changed before commiting to the .txt files, making for a better viewing experience. It also offers a score incrementer and also a swap button, which is handy when the players switch colors and/or sides.

Link the .txt files outputted into OBS and you're ready to go!

![Alt text](/TeamScoreManager.png?raw=true "OBS using the output of the program")

## Clock
A small java program to output the current time of day or a countdown into two .txt files.

![Alt text](/Timer.png?raw=true "The timer")