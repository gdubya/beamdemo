# beamdemo
Short demo of Apache Beam for Bouvet TechONE, February 2017.

## House price data
The CSV files containing house price data can be downloaded from [data.gov.uk](https://data.gov.uk/dataset/land-registry-monthly-price-paid-data).

The demo expects the files to be placed in a folder named "data".

## Running the demo
The shell scripts can be used to run the examples.

### "Local" processing
The scripts starting with "0" use basic Linux utils to prepare the data, just to demonstrate that "distributed" isn't always "better" for simple use cases ;)

### Apache Beam "Direct" runner
`1-direct.sh` uses [the "Direct" implementation](https://beam.apache.org/documentation/runners/direct/) of a Beam runner, processing the data on your local PC.

### Apache Beam "Flink" runner
`2-flinklocal.sh` uses [the "Flink" implementation](https://beam.apache.org/documentation/runners/flink/) to spin up instances of Flink, however Flink is configured in "Local" mode so the Flink cluster is still located on your own machine.

### Apache Beam "Google" runner
`3-google.sh` uses [the Google Cloud Dataflow implementation](https://beam.apache.org/documentation/runners/dataflow/) to process the data "in the cloud".
In this case it expects the data to already be available in a remote filestore in GCP (gs://bouvet-tech1-feb2017/pp-complete.csv), however this filestore is no longer available, so this demo will not work.

## Note
This demo hasn't been run since 2017, so it may not work with the latest version of Beam. That's a TODO. 