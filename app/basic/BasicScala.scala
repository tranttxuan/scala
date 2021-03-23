package com.particeep.test.basic

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
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

  def main(args: Array[String]) {
    // println(encodeParamsInUrl(Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")))

    // println(isEmail("xuan@gmail.com"))
    // println(isEmail(""))
    // println(isEmail("12121"))
    // println(isEmail("xuans11s!@gmail.com.com"))
    println(power(2,3))
    println(power(99,38997))
  }
}
