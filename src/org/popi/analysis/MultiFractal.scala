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

import org.popi.geom.BoxCounter
import org.popi.tools.{DataNormalizer, ScaleDefiner}
import org.popi.stat.{MathUtil, SimpleRegression}
import scala.collection.immutable.{List, Map}
import scala.collection.mutable.ListBuffer
/**
 * Calculates the Generalized dimension for particular q
 * <li> D(q) = τ(q) / q - 1
 * <br> q values => 0: capacity dimension, 1: information dimension, 2: correlation dimension, ...
 *
 * @author Emiliyan Todorov
 */
object MultiFractal {

  // sanity check because of apache.commons restrictions
  private val minimumSaturation = 4

  /**
   * @param data the input data
   * @param q the q
   * @return the mf dimension
   */
  def multiFractals(data:List[List[Double]], q: Double): Double = {
    val length = data.lastOption.getOrElse(List.empty).length
    val scales = ScaleDefiner.resolutions(length)
    // make normalized data to points
    val normalizedData = DataNormalizer.normalizeMultiD(data, 0L, scales.max)
    val boxesPerScale = BoxCounter.countBoxes(normalizedData.transpose, scales)
    val filtered = boxesPerScale.filter{case (_, boxes) => boxes.size >= minimumSaturation}
                                .filter{case (_, boxes) => boxes.size <= length / (minimumSaturation - 1)}
    val massScale = partitionFunction(filtered, filtered.size)
    val tauQ = partitionFunctionExponent(massScale, q)
    generalizedDimension(tauQ, q)
  }

  /**
   * Calculates the partition (or mass) function</br>
   * μ<sub>i</sub> = (points in box<sub>i</sub>) / (all points)
   *
   * @return
   */
  private def partitionFunction(boxesPerScale: Map[Long, List[Long]], totalPoints: Long): Map[Long, List[Double]] = {
    boxesPerScale.map{case (scale, boxes) => scale ->  boxes.map(points => points.toDouble / totalPoints.toDouble)}
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
   * @param q the q exponent
   *
   * @return the Partition Function exponent τ(q) (the slope)
  */
  private def partitionFunctionExponent(massesPerScale: Map[Long, List[Double]], q: Double): SimpleRegression = {
    val list = ListBuffer[(Double, Double)]()
    if(q != 1.0){
      massesPerScale.foreach{ case (scale, masses) =>
        val s = masses.foldLeft(0.0)((sum, mass) => sum + MathUtil.pow(mass, q))
        list += ((MathUtil.log2(scale), MathUtil.log2(s)))
      }
    }
    else {
      massesPerScale.foreach{ case (scale, masses) =>
        val s = masses.foldLeft(0.0)((sum, mass) => sum + (mass * MathUtil.log2(mass)))
        list += ((MathUtil.log2(scale), s))
    	}
    }
    SimpleRegression(list.toList)
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
  private def generalizedDimension(partitionFunctionExp: SimpleRegression, q: Double): Double = {
    if(q == 1.0){
      partitionFunctionExp.slope
    } else {
      partitionFunctionExp.slope / (q - 1)
    }
  }
}
