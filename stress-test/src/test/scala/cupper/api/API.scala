package cupper.api

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object User {
  val login = exec(http("login")
      .post("/users/login")
      .body(StringBody(
        """{
          |"login_id":"${login_id}",
          |"password":"${password}"
          |}""".stripMargin))
      .check(status.is(200))
      .check(jsonPath("$..token").saveAs("token"))
  )

  val logout = exec(http("logout")
      .post("/users/logout")
      .header(HttpHeaderNames.Authorization, "Bearer ${token}")
  )
}

object Item {
  val search = exec(http("search item")
      .get("/items")
      .header(HttpHeaderNames.Authorization, "Bearer ${token}")
      .check(status.is(200))
      .check(jsonPath("$[*].id").findAll.saveAs("itemIds"))
  )

  val get = exec(http("get item")
      .get("/items/${item_id}")
      .header(HttpHeaderNames.Authorization, "Bearer ${token}")
  )
}

object ShoppingCart {
  val registerItem = exec(http("register item")
      .post("/cart/items/${item_id}")
      .header(HttpHeaderNames.Authorization, "Bearer ${token}")
  )

  val purchaseItems = exec(http("purchase items")
      .post("/cart/purchase")
      .header(HttpHeaderNames.Authorization, "Bearer ${token}")
  )

  val cancelItems = exec(http("/cart/cancel")
      .post("/cart/cancel")
  )
}
