FROM openjdk:8-jre

MAINTAINER corn

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8002 \
    PROFILES="default"
WORKDIR /opt/
#ADD /target/*.jar /gateway.jar
ADD ./*.jar /istio-a-service.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /istio-a-service.jar --spring.profiles.active=$PROFILES"]

EXPOSE $PORT
####docker build -f Dockerfile -t corn/istio-a-service:1.0.0 .
