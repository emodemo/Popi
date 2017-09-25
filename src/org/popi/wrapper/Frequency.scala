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

package org.popi.wrapper

import scala.collection.immutable.List

/**
 * A wrapper for frequencies. Currently it uses apache.commons but may change in the future
 *
 * @author Emiliyan Todorov
 *
 */
class Frequency {

  private val frequency = new org.apache.commons.math3.stat.Frequency

  /**
   * Initialization
   *
   * @param data the input data
   */
  def init(data: List[Double]): Unit = {
    data.foreach(dataItem => frequency.addValue(dataItem))
  }

  /**
   * Returns the cumulative percentage for a give data item based on the initlized input data
   * @param value the data item
   * @return the cumulative percentage
   */
  def cumulativePercentage(value: Double): Double = frequency.getCumPct(value)

}
