ARG username=sanjay
#Creating JAR file
FROM maven:3.6.3-openjdk-17-slim AS stage1
WORKDIR /app/home
COPY . /app/home/
RUN mvn -f /app/home/pom.xml clean package

#Creating Image file
FROM openjdk:17-ea-17-jdk-slim AS stage2
ENV port=8080

EXPOSE ${port}
COPY --from=stage1 /app/home/target/spring-security-jwt.jar spring-security-jwt.jar
ENTRYPOINT [ "sh","-c", "java -jar spring-security-jwt.jar" ]