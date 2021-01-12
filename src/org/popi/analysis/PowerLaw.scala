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
import org.popi.stat.{toLog, Frequency, SimpleRegression}

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
   * Calculates the Power Law Exponent α
   *
   * @param data the input data
   * @return the Power Law Exponent
   */
  def powerLawExponent(data: List[Double]): SimpleRegression = {
    val sortedData = data.sorted
    val frequency = Frequency(sortedData)
    // remove the biggest (here - the last) value for correct calculation
    val sorted = sortedData.dropRight(1)
    val ccdf = sorted.map(item => (item, 1 - frequency.cumulativePercentage(item)))
    SimpleRegression(toLog(ccdf))
  }
}
