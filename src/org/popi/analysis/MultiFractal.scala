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
import scala.collection.immutable.Map
import org.popi.tools.DataNormalizer
import org.popi.tools.ScaleDefiner
import org.popi.geom.NDimensionalPoint
import org.popi.geom.BoxCounter
import org.popi.geom.NDimensionalBox
import org.popi.analysis.result.RegressionSlope
import org.popi.wrapper.MathUtil
import scala.collection.mutable.ListBuffer
import org.popi.analysis.result.RegressionSlopeType
/**
 * Calculates the Generalized dimension for particular q
 * <li> D(q) = τ(q) / q -1
 * <br> q values => 0: capacity dimension, 1: information dimension, 2: correlation dimension, ...
 *
 * @author Emiliyan Todorov
 */
object MultiFractal {

  // sanity check because of apache.commons restrictions
  private val MINIMUM_SATURATION = 4

  /**
   * @param data the input data
   * @param q the q
   * @return the mf dimension
   */
  def multiFractals(data:List[List[Double]], q: Double): Double = {
    val massScale = massesPerScale(data)
    val tauQ = partitionFunctionExponent(massScale, q)
    generalizedDimension(tauQ, q)
  }

  private def massesPerScale(data:List[List[Double]]): Map[Long, List[Double]] = {
    val inputdataSize = data.last.size
    val scales = ScaleDefiner.defineScaleResolutions(inputdataSize)
    // make normalized data to points
    val normalizedData = DataNormalizer.normalizeMultiD(data, 0L, scales.max.toLong)
    val points = normalizedData.transpose.map(point => new NDimensionalPoint(point))
    // box counting
    val boxesPerScale = BoxCounter.countBoxes(points, scales.map(scale => scale.toLong))
    val filtered = boxesPerScale.filter(entry => entry._2.size >= MINIMUM_SATURATION).filter(entry => entry._2.size <= inputdataSize / (MINIMUM_SATURATION - 1))
    // TODO: inputDataSize is not quite correct to be used here as some of the points might be filtered
    massFunction(filtered, inputdataSize)
  }

  /**
   * Calculates the mass function</br>
   * μ<sub>i</sub> = (points in box<sub>i</sub>) / (all points)
   *
   * @return
   */
  private def massFunction(boxesPerScale: Map[Long, List[NDimensionalBox]], totalPoints: Long): Map[Long, List[Double]] = {
    boxesPerScale.map(scale => scale._1 ->  scale._2.map(box => (box.getNumberOfPoints.toDouble / totalPoints.toDouble)))
  }

 /**
   * Calculates the Partition Function (or Mass) exponent τ(q) for particular q</br>
   * τ(q) ~ log(Σ(μ<sup>q</sup>(r))) / log(r), where μ is the mass function</br>
   * τ(q) ~ log(Σ(p<sup>q</sup>(Δt))) / log(Δt), for time series</br>
   * <b>Notes:</b>
   * <li>(q-1)D(q) = τ(q)
   * <li>μ<sub>i</sub>(r) = points in box / all points
   * <li>p<sub>i</sub>(Δt) = |(X(t<sub>i</sub>+Δt) - X(t<sub>i</sub>)| / Σ|(X(t<sub>i</sub>+Δt) - X(t<sub>i</sub>)|
   *
   * @param massesPerScale map with key - the scale resolution and value - the masses for the scale
   * @param q the q
   *
   * @return RegressionSlope, where the slope gives the Partition Function exponent τ(q)
  */
  private def partitionFunctionExponent(massesPerScale: Map[Long, List[Double]], q: Double): RegressionSlope = {
	  // TODO: too much Java like
	  var list = List[(Double, Double)]()
    if(q != 1.0){
    	massesPerScale.foreach(entry => {
				val s = entry._2.foldLeft(0.0)((sum, mass) => sum + MathUtil.pow(mass, q))
				list = list.:+((MathUtil.log2(entry._1), MathUtil.log2(s)))
    	})
    }
    else {
      massesPerScale.foreach(entry => {
        val s = entry._2.foldLeft(0.0)((sum, mass) => sum + (mass * MathUtil.log2(mass)))
				list = list.:+((MathUtil.log2(entry._1), s))
    	})
    }

    RegressionSlope(RegressionSlopeType.NonLogarithmic, list)
  }

  /**
   * Calculates the Generalized dimension for particular q</br>
   * D(q) = τ(q) / (q - 1) </br>
   *
   * @param partitionFunctionExp the τ(q)
   * @param q the q
   *
   * @return the D(q)
   */
  private def generalizedDimension(partitionFunction: RegressionSlope, q: Double): Double = {
    if(q == 1.0){
      partitionFunction.slope
    } else {
      partitionFunction.slope / (q - 1)
    }
  }
}
