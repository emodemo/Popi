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
import scala.collection.mutable.ListBuffer
import org.popi.wrapper.MathUtil
import org.popi.tools.ScaleDefiner
import org.popi.tools.DataScaler
import scala.collection.immutable.Map
import org.popi.analysis.result.RegressionSlope
import org.popi.analysis.result.RegressionSlopeType
/**
 * Performs the Hurst Exponent Analysis, which gives the Rescaled Ranges over
 * scales of observations. It is based on Mandelbrot.
 *
 * @author Emiliyan Todorov
 */
object HurstExponent {

  /**
   * Performs the Hurst Exponent Analysis
   *
   * @param data the input data
   *
   * @return a {@link RegressionSlope} for Hurst Exponent
   */
  def hurstExponent(data: List[Double]): RegressionSlope = {
    val scaleSizes  = ScaleDefiner.defineScaleSizes(data.size)
    val scaledData = DataScaler.scaleBySize(data, scaleSizes)
    val tuples = scaledData.map(entry => {
       val sum = entry._2.map(item => rescaledRange(item)).sum
       (entry._1.toDouble, sum / entry._2.size)
    })
    RegressionSlope(RegressionSlopeType.Logarithmic, tuples.toList)
   }


  /**
   * Calculate Rescaled Range statistics for given interval or section.</br>
   * The following calculations are done: <li>
   * mean of the range: m = 1/n Σ(all X) <li>deviation form the mean: Yi = (Xi
   * - m) <li>cumulativeSum of deviations: Zi = sum(Y1 to Yi) <li>min and max
   * of cumulative sum of deviations: max(all Z) - min(all Z) <li>Standard
   * deviation of the population (not of the sample): sqrt(1/n * Σ(Xi-m)^2)
   * <li>rescaled range
   *
   * @param data the inut data
   * @return the rescaled range for the given data
   */
  def rescaledRange(data: List[Double]): Double = {
    val average = data.sum / data.size
    val deviations = data.map(item => item - average)
    var deviationsCumsum = 0.0
    val deviationsCumsums = ListBuffer[Double]()
    var deviationsPowsum = 0.0

    deviations.foreach(deviation => {
      deviationsCumsum += deviation
 		  deviationsPowsum += MathUtil.pow(deviation, 2)
      deviationsCumsums += deviationsCumsum
    })

    val range = deviationsCumsums.max - deviationsCumsums.min
    val sdPopulation = MathUtil.sqrt(deviationsPowsum / data.size)
    val result = range / sdPopulation
    if(result.isNaN()) { 0 }
    else { result }
  }
}
