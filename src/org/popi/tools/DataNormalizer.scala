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

package org.popi.tools

import scala.collection.immutable.List
import scala.collection.immutable.Map

/**
 * Normalized data between given ranges.</br>
 * Basic example: set S{x1,x2,...,xn} is to be rescaled from Min to Max => </br>
 * Xstd = (xi - S.min) / (S.max - S.min) </br>
 * Xscaled = Xstd * (Max-Min) + Min
 *
 * @author Emiliyan Todorov
 *
 */
object DataNormalizer {

 /**
  * Normalize one dimensional data between ranges min and max
  * @param data the input data
  * @param min the new range minimum
  * @param max the new range maximum
  * @return normalized data
  */
  def normalize(data: List[Double], min: Long, max: Long): List[Long] = {
     val dataMin = data.min
     val dataMax = data.max
     val dataDiff = dataMax - dataMin
     val scores = data.map(item => (item - dataMin) / dataDiff)

     val newRangeDiff = max - min
     val scoresScaled = scores.map(score => score * newRangeDiff + min)
     scoresScaled.map(score => score.toLong)
   }

 /**
  * Normalize multi dimensional data between ranges min and max
  * @param data key = dimension number, value = values of the dimension
  * @param min the new range minimum
  * @param max the new range maximum
  * @return normalized data map with key = dimension number, value = values of the dimension
  */
  def normalizeMultiD(data: Map[Int, List[Double]], min: Long, max: Long): Map[Int, List[Long]] = {
    data.map(dimensionEntry => dimensionEntry._1 -> normalize(dimensionEntry._2, min, max))
  }

  /**
  * Normalize multi dimensional data between ranges min and max
  * @param data list of dimensions
  * @param min the new range minimum
  * @param max the new range maximum
  * @return list of normalized dimensions
  */
  def normalizeMultiD(data: List[List[Double]], min: Long, max: Long): List[List[Long]] = {
    data.map(dimension => normalize(dimension, min, max))
  }
}
