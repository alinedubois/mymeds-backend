#!/bin/bash

docker rm -v -f postgres | true
docker rm -v -f mymeds | true
docker run -d --name postgres -v ~/postgresql-data:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=postgres -p 5432:5432 postgres:alpine
docker run -d --name mymeds -e SPRING_DATASOURCE_URL=jdbc:postgresql://192.168.1.44:5432/postgres -p 8081:8081 alinedubois/mymeds:latest
