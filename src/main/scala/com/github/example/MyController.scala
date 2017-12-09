package com.github.example

import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Singleton

import play.api.libs.json.{Json, Writes}
import play.api.mvc._


final case class CountMessage(count: Int)

final case class WelcomeMessage(message: String)


@Singleton
class MyController extends InjectedController {

  implicit val messageWrites: Writes[WelcomeMessage] = Json.writes[WelcomeMessage]
  implicit val countWrites: Writes[CountMessage] = Json.writes[CountMessage]


  private val counter = new AtomicInteger


  /**
    * An action that returns some JSON with a welcome message
    */
  def index: Action[AnyContent] = Action {
    Ok(Json.toJson(WelcomeMessage("Hello World!")))
  }


  /**
    * An action that responds with current count.
    */
  def count: Action[AnyContent] = Action {
    Ok(Json.toJson(CountMessage(counter.incrementAndGet)))
  }


}

