package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

   def compute(id: String) = {
        val aggFut = for{
        f1Result <- Webservice1.call(id)
        f2Result <- Webservice2.call(id)
      } yield (f1Result, f2Result) // return Future

      var result = Await.result(aggFut, Duration.Inf)

      val res1 = result._1 match {
        case None => 0
        case Some(value) => value
      }

      val res2 = result._2 match {
        case Left(value) => 0
        case Right(value) => value
      }

      return res1 + res2
  }

}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
