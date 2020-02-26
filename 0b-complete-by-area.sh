#!/bin/bash
awk -F "\"*,\"*" '{ price = $2; area=$14; if (price > m[area]) m[area] = price} END { for (x in m) {print x,m[x]}}' data/all/pp-complete.csv | sort
