FROM 172.16.40.63:5000/baseimage

VOLUME /tmp

MAINTAINER bossnine

ARG JAR_FILE

ADD  target/${JAR_FILE} security-user.jar

EXPOSE 8080

ENV PROFILES_OPTS=""

ENTRYPOINT [ "sh", "-c","java   -jar /security-user.jar"]