#! /bin/sh


docker ps -a | awk '{ print $1,$2 }' | grep magnoabreu/pointgen-db:1.0 | awk '{print $1 }' | xargs -I {} docker rm -f {}
docker rmi magnoabreu/pointgen-db:1.0
docker build --tag=magnoabreu/pointgen-db:1.0 --rm=true .

docker run --name pointgen-db --hostname=pointgen-db \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASS=admin \
-e POSTGRES_DBNAME=pointgen \
-e ALLOW_IP_RANGE='0.0.0.0/0' \
-v /etc/localtime:/etc/localtime:ro \
-p 36701:5432 \
-e POSTGRES_MULTIPLE_EXTENSIONS=postgis,hstore,postgis_topology \
-v /srv/pointgen-db/:/var/lib/postgresql/ \
-d magnoabreu/pointgen-db:1.0

