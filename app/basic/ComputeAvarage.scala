package com.particeep.test.basic

/**
 * Compute the avarage of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAvarage {

  def average(l: List[Double]) = {
    if(l == null || l.length == 0) {
      return 0;
    }

    var sum: Int    = 0;
    var length: Int = l.length;

    l.foreach(i => {
      sum += i;
    })

    return sum / length;
  }

}
