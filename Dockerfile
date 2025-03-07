#build angular app
FROM node:22 AS ngbuild

WORKDIR /client

# install angular cli
# check version of your own angular cli
RUN npm install -g @angular/cli@19.2.1 

COPY client/angular.json .
COPY client/package.json .
COPY client/tsconfig.json .
COPY client/tsconfig.app.json .
COPY client/src src
# below is for pwa to check the exact file name
COPY client/ngsw-config.json .

RUN npi i
RUN npm cl
RUN ng build

#Stage 2 - build spring boot
FROM openjdk:23 AS javabuild

# to put all spring boot code into server folder
WORKDIR /server

COPY server/pom.xml .
COPY server/.mvn .mvn
COPY server/mvnw .
COPY server/src src

# to check the path when ng build
# if budget exceed error: change to 4mb in the angular.json file under budgets
COPY --from=ngbuild /client/dist/client/browser src/main/resources/static

RUN chmod a+x mvnw
RUN ./mvnw package -Dmaven.test.skip=true

# run container
FROM openjdk:23

WORKDIR /app

COPY --from=javabuild /server/target/server-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE ${PORT}
ENTRYPOINT [ "java", "-jar", "app.jar" ]