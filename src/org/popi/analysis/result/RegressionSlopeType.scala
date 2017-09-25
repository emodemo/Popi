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

/**
 * {@link RegressionSlope}'s type
 * @author Emiliyan Todorov
 *
 */
object RegressionSlopeType extends Enumeration {

 /**
  * {@link RegressionSlope} type
  */
  type RegressionSlopeType = Value

  /**
   * <li>Logarithmic - the calculation should be made with the logs of the input data
   * <li>NonLogarithmic - the calculation should be made with the input data as it is
   */
  val Logarithmic, NonLogarithmic = Value
}
