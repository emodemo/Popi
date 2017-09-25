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

package org.popi.geom

import scala.collection.immutable.List
import scala.collection.mutable.ListBuffer

/**
 * Implementation of the Box Counting Algorithm
 *
 * @author Emiliyan Todorov
 *
 */
object BoxCounter {

  /**
   * Prepare all box with corresponding number of points for each scales' resolution.
   *
   * @param points the points to be placed in boxes
   * @param scaleResolutions list of all scale resolutions used to determine the size of the boxes
   *
   * @return a map with key - the scale resolution and key - the list of boxes
   */
  def countBoxes(points: List[NDimensionalPoint], scaleResolutions: List[Long]): Map[Long, List[NDimensionalBox]] = {
      scaleResolutions.map(resolution => resolution -> countBoxes(points, resolution)).toMap
  }

  /**
   * Prepare all box with corresponding number of points for each scales' resolution.
   *
   * @param points the points to be placed in boxes
   * @param scaleResolution the scale resolution used to determine the size of the boxes
   *
   * @return a list of boxes for that particular resolution
   */
  def countBoxes(points: List[NDimensionalPoint], scaleResolution: Long): List[NDimensionalBox] = {
    // TODO: too much Java like
    var boxes = ListBuffer[NDimensionalBox]()
    points.foreach(point => {
      var startCoordinate = ListBuffer[Long]()
      var endCoordinate = ListBuffer[Long]()
      point.coordinates.foreach(coordinate => {
        val boxNumber = (coordinate / scaleResolution) + 1
        startCoordinate += (boxNumber - 1) * scaleResolution
        endCoordinate += boxNumber * scaleResolution
      })
      val box = new NDimensionalBox(scaleResolution, startCoordinate.toList, endCoordinate.toList)
      val boxIndex = boxes.indexOf(box)
      if(boxIndex == -1) { // if box does not exist
        box.addAPoint()
        boxes += box
      } else { // if box exists
        boxes(boxIndex).addAPoint()
      }
    })
    boxes.toList
  }
}
