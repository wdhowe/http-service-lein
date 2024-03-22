# http-service-lein

[![Build Status][gh-actions-badge]][gh-actions] [![Clojure version][clojure-v]](project.clj)

HTTP service template with Leiningen.

## Build and Run - lein/jar

Building and running the http service using the lein project tools.

### Build Standalone Uberjar

The standalone uberjar can be built with lein and run with the java -jar command.

Build the uberjar

```bash
lein uberjar
```

### Run With Lein or Jar

* Export environment variables for server settings if wanting to override defaults.

  Example
  
  ```bash
  # Port the http-server will listen on (default: 8080)
  export HTTP_PORT=8000
  ```

* Run the http-server
  * With lein

    ```bash
    lein run
    ```

  * With the built uberjar

    ```bash
    java -jar target/uberjar/http-service-lein-1.0.0-standalone.jar
    ```

## Build and Run - docker

Building and running the http service with docker.

### Build the Docker Image

The docker image is built and tagged by running:

```bash
make build
```

### Run Docker Container

Different ways to run the development/testing container.

* Run an interactive container.
  * control+c to stop/remove the container.

  ```bash
  make docker-run
  ```

* Run a container and connect with a shell.
  * Type "exit" to stop/remove the container.

  ```bash
  make shell
  ```

* Run a container in detached mode.
  * Stop the container using "docker stop `CONTAINER ID`". It will be deleted upon stop.

  ```bash
  make docker-run-detach
  ```

## Client requests

Send client requests to the running http-server.

### Routes

Available default routes are:

* GET /help  -> return plain text help.
* GET /healthy  -> return json of the health check response.

### Examples

Example output using [httpie](https://httpie.io/).

help

```bash
http http://localhost:8080/help
```

```bash
HTTP/1.1 200 OK
Content-Type: text/plain; charset=utf-8
Date: Fri, 26 Jan 2024 02:19:36 GMT
Server: http-kit
content-length: 140

HTTP API (1.0.0). Send requests to:
GET /help -> This help dialog.
GET /healthy -> Application health check.
```

healthy

```bash
http http://localhost:8080/healthy
```

```bash
HTTP/1.1 200 OK
Content-Type: application/json; charset=utf-8
Date: Fri, 26 Jan 2024 02:26:14 GMT
Server: http-kit
content-length: 16

{
    "healthy": true
}
```

## License

Copyright © 2021-2024 Bill Howe

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
`http://www.eclipse.org/legal/epl-2.0.`

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at `https://www.gnu.org/software/classpath/license.html`.

<!-- Named page links below: /-->

[gh-actions-badge]: https://github.com/wdhowe/http-service-lein/workflows/ci%2Fcd/badge.svg
[gh-actions]: https://github.com/wdhowe/http-service-lein/actions
[clojure-v]: https://img.shields.io/badge/clojure-1.11.1-blue.svg
