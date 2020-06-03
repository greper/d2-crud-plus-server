FROM java:8-jre
EXPOSE 7070
ARG project_dir="."
COPY ${project_dir}/target/api-server.jar /app/
ENTRYPOINT exec java $JAVA_OPTS  -jar  /app/api-server.jar
