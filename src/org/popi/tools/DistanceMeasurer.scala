/**
 *  Copyright (C) Emiliyan Todorov.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.popi.tools

import org.apache.commons.math3.ml.distance.EuclideanDistance
import scala.collection.immutable.List
import org.popi.wrapper.MathUtil

/**
 * Distance Measurer
 * @author Emiliyan Todorov
 *
 */
object DistanceMeasurer {

  /**
   * Calculates the Euclidean distance between two multi dimensional points
   * @param point1 the coordinates for point 1
   * @param point2 the coordinates for point 2
   * @return the Euclidean distance
   */
  def euclideanDistance(point1: List[Double], point2: List[Double]): Double = {
    val dp = point1.zip(point2).map(coordinates => coordinates._1 - coordinates._2)
    val sum = dp.map(difference => difference * difference).sum
    MathUtil.sqrt(sum)
  }
}
