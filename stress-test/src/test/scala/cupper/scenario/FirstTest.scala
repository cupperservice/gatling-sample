package cupper.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

import cupper.config.Config

class FirstTest extends Simulation with Config {
  val protocol = http
    .baseUrl(TARGET)
    .headers(Map(
      HttpHeaderNames.ContentType -> HttpHeaderValues.ApplicationJson,
      HttpHeaderNames.UserAgent -> "gatling"
    ))

  val user1 = scenario("first test")
    .exec(http("get items")
        .get("/items")
        .check(status.is(200))
    )

  setUp(user1.inject(rampUsers(60) during(30 seconds)).protocols(protocol))
}
