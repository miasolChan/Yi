FROM java:8

COPY *.jar /yi.jar

CMD ["--server.port = 8000"]

EXPOSE 8000

ENTRYPOINT ["java","-jar","/yi.jar"]