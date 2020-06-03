FROM java:8-jre
EXPOSE 7070
COPY ./api-server.jar /app/
ENTRYPOINT exec java $JAVA_OPTS  -jar  /app/api-server.jar
