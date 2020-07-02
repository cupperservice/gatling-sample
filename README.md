# How to run on the local machine
## Requirements
* Docker 19 or higher
* Docker Compose 1.25 or higher

## How to run
### Environment Variable
Set the following environment variable.
* TARGET

See `.envrc`

### Start the docker container;
`docker-compose up -d`

### Attach the container
`docker-compose exec stress-test /bin/bash`

### Start sbt
```
cd /stress-test
sbt
```

### Run a test
* run all tests
```
gatling:test
```

* run a specific test
```
gatling:testOnly cupper.scenario.FirstTest
```
or
```
gatling:testOnly *FirstTest
```

# See more details about gatling
[Gatling](https://gatling.io/)
