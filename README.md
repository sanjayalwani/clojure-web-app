# Clojure Web App
Built with the purpose of learning web development in Clojure.

## Run
### Run the app with hot-reloading
`clj -X web-app.core/-main :dev-mode true`

Add `:port 8080` to specify a port (e.g. port 8080).

Website available at http://localhost:3000 or at the port specified.

### Run a standalone jar file
`clj -T:build uberjar`

`java -jar target/web-app-0.1.0-standalone.jar`

Website available at http://localhost:3000.
