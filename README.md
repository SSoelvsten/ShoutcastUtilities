# Shoutcast Utilities
These are a collection of programs I've made to make the running of a stream a little less cumbersome. Currently it only consists of two programs and also a subservice. Many thanks to Kristian 'Yurippe' for his massive amount of help.

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

With 0.1.1b this software also includes a pause notification, which automatically includes the teamname and has an optional pause description available. When there's no pause notification, the .txt file is empty to not show the text on the pause screen.

With 0.1.2b this software also includes a BO X output, calculating the current game number based on the sum of the scores and a custom series size.

## Clock
A small java program to output the current time of day and/or a countdown into two .txt files. This might be stressful on a harddrive, but it works.

![Alt text](/Clock.png?raw=true "The timer")

## Minimalist Changelog
The following is a small overview of the implemented features

### Pre 0.1.0
- Team Score manager implemented
- Scrappy and hardcoded keylogger implemented

### 0.1.0
- Configuration file for output and keylogger properly done (Thanks Yurippe)
- Countdown / Clock implemented

### 0.1.1b
- Pause notification included

### 0.1.2b
- Game number and BO X output added
- Added default values for missing values in .cfg file
- Added check for the correct valuetypes in .cfg file

## TODO:
- Make the amount of clocks customizable
- Put everything into one singular window?
- Recreate this software in Python ( <3 )
