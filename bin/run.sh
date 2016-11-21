#!/bin/bash

loggerPath=app/config/logger.properties
parametersPath=app/config/parameters.properties

if [ ! -f $loggerPath ] || [ ! -f $parametersPath ]; then
    sh bin/compile.sh
fi

java -cp out/production/ main.MainJavaFX
