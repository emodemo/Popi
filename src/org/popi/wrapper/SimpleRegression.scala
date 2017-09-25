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
 * A wrapper for simple regression. Currently it uses apache.commons but may change in the future
 *
 * @author Emiliyan Todorov
 *
 */
class SimpleRegression {

  private val simpleRegression = new org.apache.commons.math3.stat.regression.SimpleRegression()

  /**
   * Initialization
   *
   * @param xyAxis the X and Y axes
   */
  def init(xyAxis: List[Tuple2[Double, Double]]): Unit = {
    val array = Array.ofDim[Double](xyAxis.size, 2)
    for{i <- 0 until xyAxis.size} {
      array(i)(0) = xyAxis(i)._1
      array(i)(1) = xyAxis(i)._2
    }
    simpleRegression.addData(array)
  }

  /** @return the slope */
  def slope: Double = simpleRegression.getSlope

  /** @return the slopeCI */
  def slopeCI: Double = simpleRegression.getSlopeConfidenceInterval

  /** @return the ssr */
  def ssr: Double = simpleRegression.getSumSquaredErrors

  /** @return the correlation */
  def r: Double = simpleRegression.getR

  /** @return the square of the correlation */
  def rSquare: Double = simpleRegression.getRSquare

  /** @return the significance */
  def significance: Double = simpleRegression.getSignificance

  /** @return the regression's intercept */
  def intercept: Double = simpleRegression.getIntercept
}
