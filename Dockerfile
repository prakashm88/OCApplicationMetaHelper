FROM amazoncorretto:21-alpine-jdk
LABEL maintainer="admin@itechgenie.com"

#RUN mkdir -p /usr/local/newrelic
#ADD ./newrelic-java/newrelic/newrelic.jar /usr/local/newrelic/newrelic.jar
#ADD ./newrelic.yml /usr/local/newrelic/newrelic.yml

WORKDIR /apps

COPY target/application.jar application.jar 

ENTRYPOINT ["java","-jar","/apps/application.jar"]
