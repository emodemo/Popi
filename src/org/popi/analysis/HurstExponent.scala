/**
  * Copyright (C) Emiliyan Todorov.
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful, but
  * WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */

package org.popi.analysis

import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer
import org.popi.tools.{DataScaler, ScaleDefiner}
import org.popi.stat.{toLog, MathUtil, SimpleRegression}

/**
  * Performs the Hurst Exponent Analysis, which gives the Rescaled Ranges over
  * scales of observations. It is based on Mandelbrot.
  *
  * @author Emiliyan Todorov
  */
object HurstExponent {

  /**
    * Performs the Hurst Exponent Analysis </br>
    * H = log(R/S) / log(Δt)
    *
    * @param data the input data
    * @return a { @link RegressionSlope} for Hurst Exponent
    */
  def hurstExponent(data: List[Double]): SimpleRegression = {
    val scaleSizes = ScaleDefiner.resolutionsAndSizes(data.length)
    val scaledData = DataScaler.scaleBySize(data, scaleSizes)
    val tuples = scaledData.map {
      case (scale, items) =>
      val sum = items.map(item => rescaledRange(item)).sum
      (scale, sum / items.length)
    }
    SimpleRegression(toLog(tuples.toList))
  }


  /**
    * Calculate Rescaled Range statistics for given interval or section.</br>
    * The following calculations are done:
    * <li>mean of the range: m = 1/n Σ(all X)
    * <li>deviation form the mean: Yi = (Xi - m)
    * <li>cumulativeSum of deviations: Zi = sum(Y1 to Yi)
    * <li>min and max of cumulative sum of deviations: max(all Z) - min(all Z)
    * <li>Standard deviation of the population (not of the sample): sqrt(1/n * Σ(Xi-m)^^2)
    * <li>rescaled range
    *
    * @param data the input data
    * @return the rescaled range for the given data
    */
  def rescaledRange(data: List[Double]): Double = {
    val average = data.sum / data.size
    val deviations = data.map(item => item - average)
    var deviationsCumSum = 0.0
    val deviationsCumSums = ListBuffer[Double]()
    var deviationsPowSum = 0.0

    deviations.foreach(deviation => {
      deviationsCumSum += deviation
      deviationsPowSum += MathUtil.pow(deviation, 2)
      deviationsCumSums += deviationsCumSum
    })

    val range = deviationsCumSums.max - deviationsCumSums.min
    val sdPopulation = MathUtil.sqrt(deviationsPowSum / data.size)
    range / sdPopulation
  }
}
