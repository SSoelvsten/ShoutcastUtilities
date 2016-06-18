# Shoutcast Utilities
These are a collection of programs I've made to make the running of a stream a little less cumbersome. Currently it only consists of one program.

## Global shortcuts
By using JNativeHook is also a keylogger implemented, which reacts to certain key-combinations. Change the overlay without having to actually defocus the game!
The keybinds are rebindable, though you have to know the integercodes for the key you wish to rebind for. Rebindings can be done in "bindings.txt" in exactly the same order as specified below in the standard bindings.

- ALT GR: Universal meta-key as identifier. This key has to be pressed for the others to work.
- Numpad 0: Apply changes
- Numpad 7: Team Score Manager: Increase score for Team A
- Numpad 4: Team Score Manager: Decrease score for Team A
- Numpad 9: Team Score Manager: Increase score for Team B
- Numpad 6: Team Score Manager: Decrease score for Team B
- Numpad 8: Team Score Manager: Swap teams

## Team Score .txt Manager
A small java program to manage the .txt files used for the text on an overlay during a shoutcast for both teams. This includes full name of the teams, their tags and the current score.
These can be changed before commiting to the the .txt files, making for a better viewing experience. It also offers a score incrementer and also a swap button, which is handy when the players switch colors and/or sides.

Shortcuts (Global)
- alt + f1 : Swap teams
- alt + f2 : Update .txt files

Link the .txt files outputted into OBS and you're ready to go!

![Alt text](/TeamScoreManager.png?raw=true "OBS using the output of the program")
