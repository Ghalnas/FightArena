#!/bin/bash

cp app/config/logger.properties.dist app/config/logger.properties
cp app/config/parameters.properties.dist app/config/parameters.properties
rm -rf out
mkdir -p out/production
find . -name "*.java" > sourcefiles
javac -s src/ -d out/production/ @sourcefiles
rm sourcefiles
java -cp out/production/ main.Main