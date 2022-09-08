FROM openjdk:8-jre

MAINTAINER corn

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    PORT=8002 \
    PROFILES="default"
WORKDIR /opt/
#ADD /target/*.jar /gateway.jar
ADD ./*.jar /istio-auth-service.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /istio-auth-service.jar --spring.profiles.active=$PROFILES"]

EXPOSE $PORT
####docker build -f Dockerfile -t corn/istio-auth-service:1.0.0 .
