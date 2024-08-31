FROM registry.cn-shenzhen.aliyuncs.com/handsfree/bitnami_java:1.8
EXPOSE 7070
COPY ./target/api-server.jar /app/
ENTRYPOINT exec java $JAVA_OPTS  -jar  /app/api-server.jar
