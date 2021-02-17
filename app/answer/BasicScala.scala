package com.particeep.test

import scala.annotation.tailrec

/**
  * This is basic language questions
  */
object BasicScala {


  /**
    * Encore parameter in url format
    *
    * Example:
    *
    * input = Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
    * output => "?sort_by=name&order_by=asc&user_id=12"
    *
    * input = Map()
    * output => ""
    */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    params.foldLeft("") { (rez, elem) =>
      val new_line = elem._1 + value_separator + elem._2
      if (rez.isEmpty) {
        new_line
      } else {
        rez + line_separator + new_line
      }
    }
  }

  /**
    * Test if a String is an email
    */
  private[this] val emailRegex = """^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$""".r
  def isEmail(maybeEmail: String): Boolean = Try {
    !maybeEmail.trim.isEmpty && emailRegex.findFirstMatchIn(maybeEmail).isDefined
  }.getOrElse(false)


  /**
    * Compute i ^ n
    *
    * Example:
    *
    * input = (i = 2, n = 3) we compute 2^3 = 2x2x2
    * output => 8
    */
  def power(i:Int, n:Int):Int = {
    @tailrec
    def power_acc(i:Int, n:Int, acc:Int) = {
      n match {
        case 0 => acc
        case _ => power_acc(i, n-1, acc * i)
      }
    }

    power_acc(i, n, 1)
  }

}
