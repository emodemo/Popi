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

import scala.collection.immutable.{List, Map}
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{breakable, break}

/**
 * Data Scalar
 *
 * @author Emiliyan Todorov
 *
 */
object DataScaler {

  // sanity check because of apache.commons restrictions
  private val MINIMUM_SATURATION = 4

 /**
  * Data is grouped for each scale depending on the scale's size
  *
  * @param data the data
  * @param scaleSizes map with entry - the scale's resolution and value the scale's size
  * @return map with key = scale's size, and value = list of groups with data items
  *
  * </br><b>Example</b>: for input data of size 100 there are: </br>
  * size 100: 1,2,3,4,...,100</br>
  * size 50:  1,2,3,4,...,50  and  51,53,54,...,100</br>
  * size 25:  1,2,3,4,...25  and  26,27,28,...,50  and  51,52,53,54,...,75  and  76,77,78,79,...,100</br>
  * and so on ...
  */
  def scaleBySize(data: List[Double], scaleSizes: Map[Long, Double]): Map[Double, List[List[Double]]] = {
    var result = Map[Double, List[List[Double]]]()
    // TODO: too much Java like
    scaleSizes.foreach{case (scale, size) => {
       var startIndex = 0
       var scaleSize = size.toInt
       var buffer = ListBuffer[List[Double]]()
       breakable {
          if(scaleSize < MINIMUM_SATURATION) { break }
          else{
            for(j <- 0 until scale.toInt) {
              buffer.+=:(data.slice(startIndex, startIndex + scaleSize))
              startIndex += scaleSize
            }
          }
       }
       if(!buffer.isEmpty){
         result += (size -> buffer.toList)
       }
     }}
    result
  }

 /**
  * Data is grouped for each scale depending on the scale's resolution
  *
  * @param data the data
  * @param scaleSizes map with entry - the scale's resolution and value the scale's size
  * @return map with key = scale's size, and value = list of groups with data items
  *
  * </br><b>Example</b>: for input data of size 100 there are: </br>
  * for scale = 1 => 100, 99, 98...</br>
  * for scale = 2 => 100, 98, 96... and 99, 97, 95 ...</br>
  * for scale = 4 => 100, 96, 92... and 99, 95, 91 ... and 98, 94,90...  and 97, 93, 89...</br>
  * and so on ...
  */
  def scaleByDeltaResolution(data: List[Double], scaleSizes: Map[Long, Double]): Map[Long, List[List[Double]]] = {
    val tmpResult = scaleSizes.map{case (scale, size) => {
      // TODO: too much Java like
      var rowDataForScale = ListBuffer[List[Double]]()
      for(i <- 0 until scale.toInt){
        var rowData = ListBuffer[Double]()
        for(j <- i until data.size by scale.toInt){
          rowData.+=(data(j))
        }
        if(rowData.size >= MINIMUM_SATURATION){
          rowDataForScale.+=(rowData.toList)
        }
      }
      scale -> rowDataForScale.toList
    }}

    tmpResult.filter{case (scale, size) => size.size >= scale}
  }
}
