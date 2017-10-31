#!/usr/bin/env bash
#nazwa kontenera u was
IMAGE_NAME=`docker ps | awk '$2 == "footbalmanager_db" { print $1 }'`
echo IMAGE_NAME
docker exec -i $IMAGE_NAME psql -U postgres -d footbal_manager -f ./docker-entrypoint-initdb.d/schema.sql
