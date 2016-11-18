#!/bin/bash

loggerPath=app/config/logger.properties
parametersPath=app/config/parameters.properties

if [ ! -f $loggerPath ] || [ ! -f $parametersPath ]; then
    cp $loggerPath.dist $loggerPath
    cp $parametersPath.dist $parametersPath
fi

rm -rf out
mkdir -p out/production
find ./src/ -name "*.java" > sourcefiles
javac -s src/ -d out/production/ @sourcefiles
rm sourcefiles