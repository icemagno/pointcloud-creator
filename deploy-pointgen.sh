#! /bin/sh

svn update
mvn clean package

docker ps -a | awk '{ print $1,$2 }' | grep magnoabreu/pointgen:1.0 | awk '{print $1 }' | xargs -I {} docker rm -f {}
docker rmi magnoabreu/pointgen:1.0
docker build --tag=magnoabreu/pointgen:1.0 --rm=true .

docker run --name pointgen --hostname=pointgen \
-e ARCHIMEDES_CONFIG_URI=http://archimedes:36206/ \
-v /etc/localtime:/etc/localtime:ro \
-v /srv/pointgen/:/pointgen/ \
-e CONFIG_PROFILES=default \
-p 36700:36700 \
-d magnoabreu/pointgen:1.0

