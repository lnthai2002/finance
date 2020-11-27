If running from a docker image, need to map port 8080 to host os

docker run --name finance -p 8080:8080 -d finance.microservice:0.0.1-SNAPSHOT