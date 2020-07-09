package cupper.scenario

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class FirstTest extends Simulation {
  val protocol = http
    .baseUrl("http://localhost:3000")
    .headers(Map(
      HttpHeaderNames.ContentType -> HttpHeaderValues.ApplicationJson,
      HttpHeaderNames.UserAgent -> "gatling"
    ))

  val user1 = scenario("first test")
    .exec(http("get movies")
        .get("/movies")
        .check(status.is(200))
    )

  setUp(user1.inject(rampUsers(1000) during(30 seconds)).protocols(protocol))
}
