import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await

object Main extends App {

def encodeParamsInUrl(params: Map[String, String]): String =
    "?" + params.map(_.productIterator.mkString("=")).mkString("&")

  /**
   * Test if a String is an email
   */
  private val emailRegex =
    """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
  def isEmail(maybeEmail: String): Boolean = maybeEmail match {
    case null                                                            => false
    case maybeEmail if maybeEmail.trim.isEmpty                           => false
    case maybeEmail if emailRegex.findFirstMatchIn(maybeEmail).isDefined => true
    case _                                                               => false
  }

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299
   */
  def power(i: Int, n: Int): Int = {
    var result = 1
    var a      = 0
    for(a <- 1 to n) {
      result *= i
    }
    result
  }


  def average(l: List[Double]): Double = {
    if(l == null || l.length == 0) {
      return 0
    }
 
    var sum:Double = 0
    var length:Int = l.length

    l.foreach(i => {
      sum += i
    })

    return sum/length
  }

    // println(encodeParamsInUrl(Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")))

    // println(average(List(1,10,16)))
    // println(isEmail())
    val id = "3"


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

    println(res1 + res2)
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