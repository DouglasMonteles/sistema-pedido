FROM openjdk:11

ARG ADDITIONAL_OPTS

ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /spring_boot

COPY /target/backend*.jar backend_api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar backend_api.jar 
