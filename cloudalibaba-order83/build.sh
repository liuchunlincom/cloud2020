#!/usr/bin/env bash

mvn package

docker build -f Dockerfile -t cloud-payent .

docker run -it -p 9001:9001 cloud-payent