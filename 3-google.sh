#!/bin/bash

mvn compile exec:java -Dexec.mainClass=no.bouvet.tech1.beam.HousePrices -Dexec.args="--runner=DataflowRunner --gcpTempLocation=gs://bouvet-tech1-feb2017/tmp"
