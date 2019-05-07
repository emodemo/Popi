package org.popi.geom

import org.popi.stat.MathUtil

import scala.collection.immutable.List

/**
 * Distance Measurer
  *
  * @author Emiliyan Todorov
 *
 */
object Distances {

  /**
   * Calculates the Euclidean distance between two multi-dimensional points
   * @param point1 the coordinates for point 1
   * @param point2 the coordinates for point 2
   * @return the Euclidean distance
   */
  def euclidean(point1: List[Double], point2: List[Double]): Double = {
    val dp = point1.zip(point2).map{case (coordinates_p1, coordinates_p2) => coordinates_p1 - coordinates_p2}
    val sum = dp.map(difference => difference * difference).sum
    MathUtil.sqrt(sum)
  }
}
