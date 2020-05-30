mvn clean package -Dmaven.test.skip=true
scp -r target/executor.jar jpa@47.98.178.129:/home/jpa/jobExecutor/builds