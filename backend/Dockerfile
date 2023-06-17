FROM debian
RUN apt update && apt install -y openjdk-17-jdk nodejs npm libnss3 libnspr4 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxcomposite-dev libxdamage1 libxfixes3 libxrandr2 libgbm1 libxkbcommon0 libasound2

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline
COPY src ./src
COPY checker ./utils
WORKDIR ./utils
RUN npm ci --cache .npm
WORKDIR /app

CMD ["./mvnw", "spring-boot:run", "-Dspring.profiles.active=prod"]
