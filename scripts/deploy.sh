#!/usr/bin/env bash


scp -i ~/Desktop/.ssh/UbuntyKey.pem \
    target/SpringBootSite-1.0-SNAPSHOT.jar  \
    ubuntu@ec2-34-239-102-97.compute-1.amazonaws.com:/home/ubuntu

echo 'Restart server...'
ssh -i ~/Desktop/.ssh/UbuntuKey.pem ubuntu@ec2-34-239-102-97.compute-1.amazonaws.com << EOF
pgrep java | xargs kill -9
nohup java -jar SpringBootSite-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'GOOD Bye'
