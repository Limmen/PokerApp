#!/bin/sh

clear
echo
mvn clean install
echo
(cd target && java -jar poker-1.0-SNAPSHOT-jar-with-dependencies.jar )
echo

