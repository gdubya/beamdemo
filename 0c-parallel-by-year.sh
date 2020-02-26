#!/bin/bash

for f in data/*.csv
do
  year=$(basename ${f%.*})
  year=${year#*-}
  awk -F "\"*,\"*" '{ price = $2; year=substr($3, 0, 5); if (price > m[year]) m[year] = price} END { for (x in m) {print x,m[x]}}' $f &
done

for job in $(jobs -p)
do
  wait $job || let "FAIL+=1"
done