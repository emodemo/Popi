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

package org.popi.geom

import org.popi.stat.MathUtil.{pow, abs, sqrt}
import scala.collection.immutable.List

/**
 * Distance Measurer
  *
  * @author Emiliyan Todorov
 *
 */
object Distances {

  /**
    * Calculates the distance between two multi-dimensional points
    * @param point1 the coordinates for point 1
    * @param point2 the coordinates for point 2
    * @return the distance
    */
  def euclidean(point1: List[Double], point2: List[Double]): Double =
    pNorm(2, point1, point2)

  /**
    * Calculates the distance between two multi-dimensional points
    * @param pnorm the p norm (e.g. >1 for Minkowski, 1 for Manhattan, 2 for Euclidean, +inf for Chebishev, ...)
    * @param point1 the coordinates for point 1
    * @param point2 the coordinates for point 2
    * @return the distance or NaN if pnorm < l.0
    */
  def pNorm(pnorm: Double, point1: List[Double], point2: List[Double]): Double = {

    def difference = point1.zip(point2).map{case (coordinates_p1, coordinates_p2) => abs(coordinates_p1 - coordinates_p2)}

    pnorm match {
        // TODO: implement for p < 1
      case _ if pnorm < 1 => Double.NaN
      case 1.0 => difference.sum
      case 2.0 =>
        val sum = difference.map(x => x * x).sum
        sqrt(sum)
      case Double.PositiveInfinity => difference.max
      // case Double.NegativeInfinity => difference.min
      case p: Double =>
        val sum = difference.map(x => pow(x, p)).sum
        pow(sum, 1/p)
    }
  }
}
