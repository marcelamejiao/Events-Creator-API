FROM maven:3-eclipse-temurin-21-alpine as base

FROM base as java-builder

WORKDIR /server

ADD pom.xml ./

# Build all dependencies for offline use
RUN mvn dependency:go-offline -B

ADD . .

# Build the Backend executable /server/target/server.jar
RUN unset MAVEN_CONFIG \
    # TODO: Build without running tests for now
#    && mvn clean package \
    && mvn clean package -Dmaven.test.skip

FROM base as app

# Install dependencies
RUN apk add --no-cache \
          supervisor \
          nginx

# Add nginx configuration
RUN rm -rf /etc/nginx/http.d
ADD docker/nginx/http.d /etc/nginx/http.d/
ADD docker/nginx/nginx.conf /etc/nginx/nginx.conf

# Add Supervisor configuration
RUN mkdir -p /etc/supervisor/conf.d
ADD docker/supervisor/supervisord.conf /etc/supervisor
ADD docker/supervisor/app.ini /etc/supervisor/conf.d

WORKDIR /app

# Add the compiled backend
COPY --from=java-builder /server/target/server.jar /app/server.jar

# Add the entrypoint and set it
ADD docker/entrypoint.sh /entrypoint.sh

ENTRYPOINT [ "/bin/sh", "/entrypoint.sh" ]