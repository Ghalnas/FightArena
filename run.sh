#!/bin/bash

loggerPath=app/config/logger.properties
parametersPath=app/config/parameters.properties

if [ ! -f $loggerPath ] || [ ! -f $parametersPath ]; then
    cp $loggerPath.dist $loggerPath
    cp $parametersPath.dist $parametersPath
fi

rm -rf out
mkdir -p out/production
find . -name "*.java" > sourcefiles
javac -s src/ -d out/production/ @sourcefiles
rm sourcefiles
java -cp out/production/ main.MainJavaFX