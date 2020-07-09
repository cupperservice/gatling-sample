package cupper.scenario

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import cupper.api.{Item, ShoppingCart, User}

import scala.util.Random

class Demo extends Simulation {
  Random.setSeed(System.currentTimeMillis())
  val httpProtocol = http
    .baseUrl("http://target:3000")
    .headers(Map(
        HttpHeaderNames.ContentType -> HttpHeaderValues.ApplicationJson,
        HttpHeaderNames.UserAgent -> "gatling"
    ))

  val feeder = Iterator.continually(
    Map(
      "login_id" -> "login_id",
      "password" -> "password"
    )
  )

  val user1 = scenario("gatling demonstration")
    .feed(feeder)
    .exec(User.login)
    .exec(session => {
      val token = session("token").as[String]
      println(token)
      session
    })
    .pause(5 seconds)
    .repeat(5) {
        exec(Item.search)
        .exec(session => {
          println(session("itemIds").as[Seq[Int]])
          session
        })
        .exec(session => session.set("item_id", Random.nextInt(10) + 1))
        .pause(5 seconds)
        .exec(Item.get)
    }
    .pause(10 seconds)
    .exec(ShoppingCart.registerItem)
    .exec(session => session.set("wouldYouBuy", Random.nextBoolean()))
    .pause(5 seconds)
    .doIfOrElse(session => session("wouldYouBuy").as[Boolean]) {
      exec(ShoppingCart.purchaseItems)
    } {
      exec(ShoppingCart.cancelItems)
    }
    .exec(User.logout)

  val profile = incrementUsersPerSec(1).times(5).eachLevelLasting(10 seconds).startingFrom(0)
//  val profile = atOnceUsers(1)

  setUp(user1.inject(profile).protocols(httpProtocol))
}
