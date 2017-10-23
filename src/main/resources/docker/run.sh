#!/usr/bin/env bash

docker rm -v $(docker ps -a -q -f status=exited) 2> /dev/null || true
docker rmi $(docker images -f "dangling=true" -q) 2> /dev/null || true

if [ $(docker ps -a | grep 'footballManager' | wc -l) -le 0 ];
then
	echo 'Container footballManager does not exists'
else
	echo 'Stopping container footballManager'
    docker stop footballManager || true
    docker wait footballManager || true
    echo 'Removing container footballManager'
    docker rm footballManager || true
fi

docker run --name footballManager -p 5432:5432 -e POSTGRES_PASSWORD=passwd -ePOSTGRES_DB=footballManager -d postgres

while [ $(docker logs footballManager | grep 'database system is ready to accept connections' | wc -l) -le 0 ];
	do echo Waiting database not started. Sleeping 5s;
	sleep 5;
done

echo Docker oracle database started
sleep 1;