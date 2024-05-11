FROM maven:3.8.5-openjdk-17
WORKDIR /login-portal-dyt
COPY . .
RUN mvn clean package
CMD mvn spring-boot:run