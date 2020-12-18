## Start docker containers
docker-compose up -d

## Run Gatling
1. login attacker
docker-compose exec attacker bash

2. run sbt
cd stress-test
sbt

* All tests:
```
gatling:test
```

Single test:
```
gatling:testOnly cupper.scenario.Demo
```

3. Place of the reports
stress-test/target/gatling

## Run locust
http://localhost:8089
