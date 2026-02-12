FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle :app:installDist --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/app/build/install/app ./
ENTRYPOINT ["./bin/app"]
