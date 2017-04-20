FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD spring-jpa.jar spring-jpa.jar
RUN sh -c 'touch /spring-jpa.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /spring-jpa.jar" ]
