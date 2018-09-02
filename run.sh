#!/usr/bin/env bash
./exim.sh
mvn clean package -DskipTests
java -jar target/boot-hst-0.0.1-SNAPSHOT.jar -Dorg.apache.jackrabbit.core.cluster.node_id=boot_hst -Drepo.path=storage -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
