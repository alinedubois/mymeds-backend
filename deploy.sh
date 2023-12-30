#!/bin/bash

git pull
docker build -t alinedubois/mymeds:latest .
docker rm -v -f mymeds | true
docker-compose up -d
docker run -d --name mymeds -p 8080:8080 alinedubois/mymeds:latest
