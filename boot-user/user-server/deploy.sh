#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip
scp -r target/user.jar userserver@127.0.0.1:/home/userserver/user/builds