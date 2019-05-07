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

import org.apache.commons.math3.util.FastMath

/**
 * Utility class for mathematical operations
 *
 * @author Emiliyan Todorov
 *
 */
object MathUtil {

  /**
   * @param value the input
   * @return the absolute value
   */
  def abs(value: Double): Double = {
     FastMath.abs(value)
  }

  /**
   * @param value the input
   * @param power the power
   * @return the power value
   */
  def pow(value: Double, power: Double): Double = {
    FastMath.pow(value, power)
  }

  /**
   * @param value the input
   * @return the sqrt value
   */
  def sqrt(value: Double): Double = {
    FastMath.sqrt(value)
  }

  /**
   * @param value the input
   * @return the natural log
   */
  def logE(value: Double): Double = {
    FastMath.log(value)
  }

  /**
   * @param value the input
   * @param base the base
   * @return the base log
   */
  def logB(value: Double, base: Double): Double = {
    FastMath.log(base, value)
  }

  /**
   * @param value the input
   * @return the 2 log
   */
  def log2(value: Double): Double = {
    logB(value, 2)
  }
}
