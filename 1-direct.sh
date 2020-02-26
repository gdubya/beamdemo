#!/bin/bash
mvn compile exec:java -Dexec.mainClass=no.bouvet.tech1.beam.HousePrices -Dexec.args="--inputFile=/home/gareth/projects/playground/beamdemo/data/pp-199[5,6].csv --output=prices-direct"
