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

import scala.collection.immutable.{List, Map}
import org.popi.analysis.result.{RegressionSlope, RegressionSlopeType}
import org.popi.tools.{DataScaler, ScaleDefiner}
import org.popi.wrapper.Frequency

/**
 * Calculates the Power Law Exponent for the input data.</br>
 * The calculations of the Power Law Exponent α is according to Mandelbrot
 * method: log-log slope of tail distribution v.s. number. Primary use is for:
 * Pr(X>x) = x<sup>(-slope)</sup>;</br>
 * Note: Pay attention when interpreting, as the formula is with "minus slope"
 *
 * @author Emiliyan Todorov
 *
 */
object PowerLaw {

  /**
   * Scales the data and calculates the Power Law exponent α for each scale.
   * @param data the input data
   * @return key = scale resolution (the delta), value = regression slope
   */
  def powerLawExponentScaled(data: List[Double]): Map[Long, RegressionSlope] = {
    val scaleSizes = ScaleDefiner.defineScaleSizes(data.size)
    val scaledData = DataScaler.scaleByDeltaResolution(data, scaleSizes)
    scaledData.map{case (scale, items) => scale -> averageSlope(items)}
  }

  /**
   * Calculates the Power Law Exponent α
   *
   * @param data the input data
   * @return the {@link RegressionSlope} for the Power Law Exponent
   */
  def powerLawExponent(data: List[Double]): RegressionSlope = {
    val sortedData = data.sorted
    val frequency = new Frequency
    frequency.init(sortedData)
    // remove the biggest (here - the last) value for correct calculation
    // tuple._1 = data tuple._2 = ccdf
    val tuples = sortedData.dropRight(1)
    val tuples2 = tuples.map(item => (item, 1 - frequency.cumulativePercentage(item)))
    RegressionSlope(RegressionSlopeType.Logarithmic, tuples2)
  }

  private def averageSlope(groupsInScale : List[List[Double]]): RegressionSlope = {
    val slopes = groupsInScale.map(group => powerLawExponent(group))
    RegressionSlope(slopes)
  }
}
