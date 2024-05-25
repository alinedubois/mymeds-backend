FROM arm32v7/maven:3.8.6-eclipse-temurin-21-focal as build

ADD . /app
WORKDIR /app
RUN mvn package -DskipTests

FROM arm32v7/eclipse-temurin:21-jre as release

WORKDIR /app
COPY --from=build /app/target/mymeds-backend-0.0.1-SNAPSHOT.jar ./
#COPY newrelic.jar /app/newrelic.jar
#COPY newrelic.yml /app/newrelic.yml

CMD java -jar mymeds-backend-0.0.1-SNAPSHOT.jar