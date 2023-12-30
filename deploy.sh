#!/bin/bash

git pull
docker build -t alinedubois/mymeds:latest .
docker rm -v -f postgres | true
docker rm -v -f mymeds | true
docker run -d --name postgres -v $(pwd)/postgresql-data:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=postgres -p 5432:5432 postgres:alpine
docker run -d --name mymeds -p 8080:8080 alinedubois/mymeds:latest
