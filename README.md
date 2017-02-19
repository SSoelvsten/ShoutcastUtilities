# Shoutcast Utilities
This is a piece of software I have made to help taking care of production for a competition stream. Using TDD and purely compositional design, you can take advantage of this softwares flexibility in the code by writing various components altering some aspects of the software.

Many thanks to Kristian 'Yurippe' Gausel for his help on the config, but also for his advice and feedback on the design.

## Game State
A small java program to manage the .txt and .png files used for the text/pictures on an overlay during a shoutcast for both teams.
- Team names and swapping their place
- Hold score and calculates the game number
- Manages the maps chosen to be played
- Teams pauses together with reason

Link the .txt and .png files outputted into OBS and you're ready to go!

![Alt text](/img/0.2_example.png?raw=true "OBS using the output of the program")

## Clock
Both as a countdown or a clock the program can output a ticking timer a .txt file. This might be stressful on a harddrive, but it works.

## Global shortcuts
By using JNativeHook a keylogger is implemented, which reacts to certain key-combinations. This way you can interact with the software without having to actually defocus the game!

The keybinds are rebindable, though you have to use the codes you can find in "keyReference.txt". Rebindings can be done in "config.cfg". The following are the standard bindings

- ALT GR: Universal meta-key as identifier. This key has to be pressed for the others to work.
- Numpad 7: Increase score for Team 1
- Numpad 4: Decrease score for Team 1
- Numpad 9: Increase score for Team 2
- Numpad 6: Decrease score for Team 2
- Numpad 8: Swas teams
- Numpad 5: Unpause

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

### 0.1.3a
- Victor or draw output into victor.txt

### 0.2.0
- Remade everything with TDD and proper compositional design.

### 0.2.1
- Adding a clock/countdown back in.
- Unpause global shortcut
- Further design changes and code cleaning
- Fixing bugs