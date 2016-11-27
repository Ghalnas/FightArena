#!/bin/bash

loggerPath=app/config/logger.properties
parametersPath=app/config/parameters.properties

if [ ! -f $loggerPath ] || [ ! -f $parametersPath ]; then
    sh bin/compile.sh
fi

java -classpath out/production:lib/gson-2.8.0.jar main.MainJavaFX
