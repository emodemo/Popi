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

package org.popi.stat

import scala.collection.immutable.List

/**
  * Holder for regression's result
  *
  * @author Emiliyan Todorov
  * @param slope        the slope
  * @param slopeCI      the confidence interval
  * @param ssr          the ssr
  * @param r            the correlation
  * @param rSquare      the square of the correlation
  * @param significance the statistical significance
  * @param intercept    the regression's intercept
  * @param regData      the data used for the regression
  *
  */
case class SimpleRegression private(slope: Double,
                                   slopeCI: Double,
                                   ssr: Double,
                                   r: Double,
                                   rSquare: Double,
                                   significance: Double,
                                   intercept: Double,
                                   regData: List[(Double, Double)])

/**
 * A wrapper for simple regression. Currently it uses apache.commons but may change in the future
 *
 * @author Emiliyan Todorov
 *
 */
object SimpleRegression {
  def apply(xyAxis: List[(Double, Double)]): SimpleRegression = {
    val sr = new org.apache.commons.math3.stat.regression.SimpleRegression()
    val array = Array.ofDim[Double](xyAxis.length, 2)
    for{i <- xyAxis.indices} {
      array(i)(0) = xyAxis(i)._1
      array(i)(1) = xyAxis(i)._2
    }
    sr.addData(array)

    new SimpleRegression(
      sr.getSlope,
      sr.getSlopeConfidenceInterval,
      sr.getSumSquaredErrors,
      sr.getR,
      sr.getRSquare,
      sr.getSignificance,
      sr.getIntercept,
      xyAxis)
  }
}
