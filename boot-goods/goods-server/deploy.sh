#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip
scp -r target/goods.jar goods@47.98.189.110:/home/goods/goods/builds