#!/bin/bash
docker-compose down
gradle build -x test #przy testach potrzeba bazy bo inaczej liquibase wypierdala wyjatkiem, pozniej dobrze by bylo jednak tu te testy puszczac XD
cp ./build/libs/footbal_manager-0.0.1-SNAPSHOT.jar ./src/main/resources/docker/dist/
docker-compose build
docker-compose up

