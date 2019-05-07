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

package org.popi.analysis

import scala.collection.immutable.List
import org.popi.stat.{toLog, MathUtil, SimpleRegression}
import org.popi.tools.ScaleDefiner

/**
 * Structure function exponent ζ(q) and q are related through H: ζ(q) = q*H(q)
 * </br> for q = 1, H(q) ≈ Hurst exponent
 * </br> for q = 2, H(q) ≈ autocorrelation function
 *
 * @author Emiliyan Todorov
 *
 */
object GeneralizedHurstExponent {

  /**
   * Calculate the Generalized Hurst exponent</br>
   * H = ζ(q) / q, where ζ(q) is the structure function exponent of the cumulative sums of the data
   *
   * @param data the input data
   * @param q the q exponent
   * @return a tuple with
   * <li> the generalized Hurst exponent
   * <li> the structure function exponent
   */
  def generalizedHurstExponent(data: List[Double], q: Double): (Double, SimpleRegression) = {
    val scaleResolutions = ScaleDefiner.resolutions(data.size)
    val structExponent = structureFunctionExponent(cumSums(data), scaleResolutions, q)
    (structExponent.slope / q, structExponent)
  }

  private def cumSums(data: List[Double]): List[Double] = {
    data.scanLeft(0.0)((cumSum, item) => cumSum + item)
  }

  /**
   * Calculate the structure function exponent ζ(q) = q*H(q). </br>
   * Returns a RegressionSlope object where the getSlope() gives the ζ(q)</br>
   *
   * @param dataCumSums cumulative sums of data
   * @param deltaTs the scales
   * @param q the q exponent
   * @return a RegressionSlope object where the getSlope() gives the ζ(q)
   */
  private def structureFunctionExponent(dataCumSums: List[Double], deltaTs: List[Long], q: Double): SimpleRegression = {
    val tuples = deltaTs.map(delta => (delta.toDouble, structureFunction(dataCumSums, delta.toInt, q)))
     // the x axis is the Δt, the y axis is the S(q)
    SimpleRegression(toLog(tuples))
  }

  /**
   * S(q) for Δt = (1/T-Δt) * Σ(from <i>t=0</i> to <i>T-Δt-1</i>) |x(t+Δt) -
   * x(t)|<sup>q</sup></br> where T is the whole data set
   *
   * @param dataCumSums cumulative sums of data
   * @param deltaT the Δt
   * @param q the q
   * @return S(q) for Δt = <|x(t+Δt) - x(t)|<sup>q</sup>>
   */
  private def structureFunction(dataCumSums: List[Double], deltaT: Int, q: Double) = {
    var sum = 0.0
    for(i <- dataCumSums.size until deltaT by -1) {
        // the formula is interpreted backwards so that the older items are omitted, and not the newest
        // ... which is not quite correct as in other analyses the opposite is true :-/
        sum = sum + MathUtil.pow(MathUtil.abs(dataCumSums(i - 1) - dataCumSums(i - 1 - deltaT)), q)
    }
    // the mean
    sum / (dataCumSums.size - deltaT)
  }

}
