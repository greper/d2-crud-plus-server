FROM java:8-jre
EXPOSE 7070
COPY ./target/api-server.jar /app/
ENTRYPOINT exec java $JAVA_OPTS  -jar  /app/api-server.jar
