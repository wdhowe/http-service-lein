# Instructions from: https://hub.docker.com/_/clojure/
FROM clojure:openjdk-11-lein

# Environment settings
ENV APP_DIR=/usr/src/app

# Setup the application directory
RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}

# Download application dependencies
COPY project.clj ${APP_DIR}
RUN lein deps

# Create the standalone uberjar and link to the created jar
COPY . ${APP_DIR}
RUN ln -s "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" app.jar

# Default http-server port, can be overridden via env var (HTTP_PORT)
EXPOSE 8080

# Set the container startup command
ENTRYPOINT ["java", "-server", "-jar", "app.jar"]
