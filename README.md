# http-service-lein

[![Build Status][gh-actions-badge]][gh-actions] [![Clojure version][clojure-v]](project.clj)

Http service template with Leiningen.

## Building

### Build Standalone Uberjar

The standalone uberjar can be built with lein and run with the java -jar command.

* Clone the project
* Build the uberjar

  ```bash
  lein uberjar
  ```

## Running

Different ways to run the service.

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
    java -jar target/uberjar/http-service-lein-0.1.0-standalone.jar
    ```

## Client requests

Send client requests to the running http-server.

### Routes

Available default routes are:

* GET /help  -> return plain text help.
* GET /config  -> return json of the http-server env settings.
* GET /healthy  -> return json of the health check response.

## License

Copyright © 2021 Bill Howe

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
[clojure-v]: https://img.shields.io/badge/clojure-1.10.3-blue.svg
