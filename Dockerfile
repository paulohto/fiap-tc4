FROM openjdk:17-alpine

EXPOSE 8080

WORKDIR /app

ENV SERVER_PORT=8080
ENV SPRING_CONFIG_MONGODB_SERVER=mongodb
ENV SPRING_CONFIG_MONGODB_URI=mongodb://root:example@localhost:27017/

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD [ "./mvnw", "spring-boot:run"]