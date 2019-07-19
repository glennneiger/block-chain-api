FROM 172.16.40.63:5000/baseimage

VOLUME /tmp

MAINTAINER bossnine

ADD  security-user.jar test-zz.jar

EXPOSE 8080

ENV PROFILES_OPTS=""

ENTRYPOINT [ "sh", "-c","java   -jar /test-zz.jar"]