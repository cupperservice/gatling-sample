# How to run on the local machine
## Requirements
* Docker 19 or higher
* Docker Compose 1.25 or higher

## Start docker containers
docker compose up -d --scale slave=1

## Run Gatling
1. login attacker
docker-compose exec attacker bash

2. run sbt
```
cd stress-test
sbt
```

3. run stress test
* All tests:
```
gatling:test
```

* Single test:
```
gatling:testOnly cupper.scenario.Demo
```

3. Place of the reports
stress-test/target/gatling

## Run locust
1. Access the following url with browser
http://localhost:8089

2. Set the following values
  * Number of users: 100
  * spawn rate: 1
  * Host: http://localhost:3000

3. Click the [Start sqarming]

# See more details about gatling
[Gatling](https://gatling.io/)
