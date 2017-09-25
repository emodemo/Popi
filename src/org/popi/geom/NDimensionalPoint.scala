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
 * Multi Dimensional point representation
 * @param coordinates the coordinate for each dimension
 *
 * @author Emiliyan Todorov
 *
 */
class NDimensionalPoint(val coordinates: List[Long])  {

  override def hashCode: Int = {
    val prime = 31
    prime + coordinates.hashCode()
  }

  override def equals(that: Any): Boolean = {
    if(that.isInstanceOf[NDimensionalPoint]) {
      canEquals(that.asInstanceOf[NDimensionalPoint]) && this.hashCode() == that.hashCode()
    } else {
      false
    }
  }

  private def canEquals(box: NDimensionalPoint): Boolean = {
      if(this.coordinates != box.coordinates) { false }
      else { true }
  }
}