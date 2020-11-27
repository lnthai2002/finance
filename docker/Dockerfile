FROM ubuntu:20.04
#need to populate package repo
RUN apt update
#need jdk, althought the code was built to target jdk 8
RUN apt install -y openjdk-11-jre-headless

VOLUME /tmp

#TODO: externalize the jar name since we do not want to modify this docker file everytime the version change
#copy over the app
ADD finance.web-0.0.1-SNAPSHOT.jar finance.web-0.0.1-SNAPSHOT.jar
#update time stamp
RUN sh -c 'touch /finance.web-0.0.1-SNAPSHOT.jar'

#start the app, the random egd is to make tomcat start faster
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/finance.web-0.0.1-SNAPSHOT.jar"]