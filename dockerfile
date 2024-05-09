FROM maven:3.8.3-openjdk-17

WORKDIR /ticketbackend
COPY . .
RUN mvn clean install -DskipTests

CMD mvn spring-boot:run