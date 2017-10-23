docker stop footballManager
docker wait footballManager
docker rm footballManager
docker run --name footballManager -p 5432:5432 -e POSTGRES_PASSWORD=passwd -ePOSTGRES_DB=footballManager -d postgres
@echo Wait 150 seconds for initialize oracle database
@echo off
timeout 150
@echo on