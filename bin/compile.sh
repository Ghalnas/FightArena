#!/bin/bash

loggerPath=app/config/logger.properties
parametersPath=app/config/parameters.properties

cp $loggerPath.dist $loggerPath
cp $parametersPath.dist $parametersPath

rm -rf out
mkdir -p out/production
find ./src/ -name "*.java" > sourcefiles
javac -classpath lib/\* -s src/ -d out/production/ @sourcefiles
rm sourcefiles