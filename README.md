# Richard Stallman fact bot

### A (very) simple program that returns random Richard Stallman "facts".

<p align="center"><img src="/img/rms.png" width="50%" height="50%"/></p>

## Running

### Locally
Start a local REPL and boot the embedded Jetty HTTP server:

    lein repl
    (require 'rms-fact-bot.web)
    (def server (rms-fact-bot.web/-main))
    
The server can also be started outside of REPL
    
    lein run -m clojure-getting-started.web

    
The app should now be running at http://localhost:5000
 
## Run Tests

    lein test

### TODOS:
Deploy on heroku.
