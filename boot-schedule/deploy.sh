mvn clean package -Dmaven.test.skip=true
scp -r target/schedule.jar jpa@127.0.0.1:/home/jpa/jpa/builds