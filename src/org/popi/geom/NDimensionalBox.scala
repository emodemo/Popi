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


/**
 * Multi-Dimensional box representation
 *
 * @param boxSize the size
 * @param fromCoordinates inclusive from
 * @param toCoordinates exclusive to
 * @param nOfPoints starting number of points staring number of points. Defaults to 0
 *
 * @author Emiliyan Todorov
 *
 */
class NDimensionalBox(val boxSize: Long, val fromCoordinates: List[Long], val toCoordinates: List[Long], private var nOfPoints: Long = 0) {

  /**
   * add one point in the box
   */
  def addAPoint(): Unit = nOfPoints += 1

  /**
   * @return the number of points for that box
   */
  def getNumberOfPoints: Long = {
    val points = nOfPoints
    points
  }

  /** @inheritdoc */
  override def hashCode: Int = {
    val prime = 31
    val hash = 32
    var result = 1
    result = prime * result + fromCoordinates.hashCode()
    result = prime * result + (boxSize ^ (boxSize >>> hash)).asInstanceOf[Int]
    result = prime * result + toCoordinates.hashCode()

    result
  }

  /** @inheritdoc */
  override def equals(that: Any): Boolean = {
    if(that.isInstanceOf[NDimensionalBox]) {
      canEquals(that.asInstanceOf[NDimensionalBox]) && this.hashCode() == that.hashCode()
    } else {
      false
    }
  }

  private def canEquals(box: NDimensionalBox): Boolean = {
    if(this.toCoordinates != box.toCoordinates) { false }
    else if(this.fromCoordinates != box.fromCoordinates) { false }
    else if(this.boxSize  != box.boxSize) { false }
    else { true }
  }
}
