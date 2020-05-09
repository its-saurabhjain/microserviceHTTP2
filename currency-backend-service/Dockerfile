FROM openjdk:9

#COPY ./target/http_client-0.0.1-SNAPSHOT.jar /usr/src/myapp/http_client-0.0.1-SNAPSHOT.jar
#CMD ["java", "-jar", "--add-modules=jdk.incubator.httpclient", "/usr/src/myapp/http_client-0.0.1-SNAPSHOT.jar"]

# The application's jar file
ARG JAR_FILE=target/microservice.sample.currency-backend-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} microservice.sample.currency-backend-service-0.0.1-SNAPSHOT.jar
# Run the jar file 
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom", "/microservice.sample.currency-backend-service-0.0.1-SNAPSHOT.jar"]
