FROM 172.16.40.63:5000/baseimage

VOLUME /tmp

MAINTAINER bossnine

ARG JAR_FILE

ADD  target/${JAR_FILE} block-chain-zuul.jar

EXPOSE 7777

ENV PROFILES_OPTS=""

ENTRYPOINT [ "sh", "-c","java $PROFILES_OPTS -jar /block-chain-zuul.jar"]