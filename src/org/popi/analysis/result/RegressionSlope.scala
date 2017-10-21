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

package org.popi.analysis.result

import scala.collection.immutable.List
import org.popi.wrapper.{MathUtil, SimpleRegression}

/**
 * Companion Object for {@link RegressionSlope}
 *
 * @author Emiliyan Todorov
 *
 */
object RegressionSlope {

  /**
   * Creates a {@link RegressionSlope}
   * @param slopeType the type of the {@link RegressionSlope}
   * @param xyAxis the data for X and Y axes
   *
   * @return the {@link RegressionSlope}
   */
  def apply(slopeType: RegressionSlopeType.RegressionSlopeType, xyAxis: List[(Double, Double)]): RegressionSlope = {
    slopeType match {
      case RegressionSlopeType.Logarithmic => createLogarithmic(xyAxis)
      case RegressionSlopeType.NonLogarithmic => createNonLogarithmic(xyAxis)
    }
  }

  /**
   * Creates an approximate average {@link RegressionSlope}
   * @param slopes the {@link RegressionSlope}
   *
   * @return the {@link RegressionSlope}
   */
  def apply(slopes: List[RegressionSlope]): RegressionSlope = {
      createAverage(slopes)
  }

  private def createNonLogarithmic(xyAxis: List[(Double, Double)]): RegressionSlope = {
    val sr = new SimpleRegression
    sr.init(xyAxis)
    new RegressionSlope(sr.slope, sr.slopeCI, sr.ssr, sr.r, sr.rSquare, sr.significance, sr.intercept, xyAxis)
  }

  private def createLogarithmic(xyAxis: List[(Double, Double)]): RegressionSlope = {
    val logLogAxis = xyAxis.map{case (x, y) => MathUtil.log2(x) -> MathUtil.log2(y)}
    val sr = new SimpleRegression
    sr.init(logLogAxis)
    new RegressionSlope(sr.slope, sr.slopeCI, sr.ssr, sr.r, sr.rSquare, sr.significance, sr.intercept, logLogAxis)
  }

  // TODO: this calculation is not correct + needs tests
  private def createAverage(slopes: List[RegressionSlope]): RegressionSlope = {
    val slopeTuples = slopes.map(slope => (slope.slope, slope.slopeCI, slope.ssr, slope.r, slope.rSquare, slope.significance, slope.intercept))
    val tuple = slopeTuples.foldLeft((0.0,0.0,0.0,0.0,0.0,0.0,0.0))((sum, next) =>
      (sum._1 + next._1, sum._2 + next._2, sum._3 + next._3, sum._4 + next._4, sum._5 + next._5, sum._6 + next._6, sum._7 + next._7)
    )
    val size = slopeTuples.size
    new RegressionSlope(tuple._1/size, tuple._2/size, tuple._3/size, tuple._4/size, tuple._5/size, tuple._6/size, tuple._7/size, List[Tuple2[Double, Double]]())
  }
}

/**
 * Holder for regression's result
 *
 * @author Emiliyan Todorov
 *
 * @param slope the slope
 * @param slopeCI the confidence interval
 * @param ssr the ssr
 * @param r the correlation
 * @param rSquare the square of the correlation
 * @param significance the statistical significance
 * @param intercept the regression's intercept
 * @param regData the data used for the regression
 *
 */
class RegressionSlope private(val slope: Double,
    val slopeCI: Double,
    val ssr: Double,
    val r: Double,
    val rSquare: Double,
    val significance: Double,
    val intercept: Double,
    val regData: List[Tuple2[Double, Double]])

