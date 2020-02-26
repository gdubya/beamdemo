#!/bin/bash
time awk -F "\"*,\"*" '{ price = $2; year=substr($3, 0, 5); if (price > m[year]) m[year] = price} END { for (x in m) {print x,m[x]}}' data/all/pp-complete.csv | sort
