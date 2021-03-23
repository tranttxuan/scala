package com.particeep.test.basic

/**
 * Compute the avarage of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAvarage {

  def average(l: List[Double]) = l.foldLeft((0.0, 1))((acc, i) => ((acc._1 + (i - acc._1) / acc._2), acc._2 + 1))._1
  
  /* For testing in command line */
  def main(args: Array[String]) {
    var list = List[Double](4, 6, 7)
    println(average(list))
  }

}
