#!/bin/bash

mvn compile exec:java -Dexec.mainClass=no.bouvet.tech1.beam.HousePrices -Dexec.args="--inputFile=./data/pp-199[5,6].csv --runner=FlinkRunner --output=prices-flinklocal"
