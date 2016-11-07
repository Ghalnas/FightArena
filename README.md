# FightArena

2D fight game between a player and a bot.

## Summary
* [Prerequisites](#prerequesits)
    - [JAVA](#java)
* [Setup](#setup)
	- [Downloading](#downloading)
    - [Configuration](#configuration)
    - [Launching](#launching)
* [Contributing](#contributing)
    - [Bug Reports & Feature Requests](#contributing)

## Prerequisites
#### Java
FightArena uses Java Standard Edition.

Download here : [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)

## Deploying

#### Downloading

Download the latest binary from the repository, or just clone it :
```bash
$ git clone https://github.com/TataneInYourFace/FightArena.git
```

#### Configuration
The configuration is handled by the configuration files in `app/config/parameters.properties` and `app/config/logger.properties`.<br>
Follow the parameters.properties.dist and logger.properties.dist files to fill them depending on your own configuration.<br>

If you are on a Unix system, set right to fightArena.sh :
```bash
$ chmod +x fightArena.sh
```

#### Launching
Just launch fightArena.sh :
```bash
$ sh fightArena.sh
```

## Contributing

#### Bug Reports & Feature Requests

Please use the [issue tracker](https://github.com/TataneInYourFace/FightArena/issues) to report any bug or feature request.