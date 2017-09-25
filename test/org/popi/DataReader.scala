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

package org.popi

import scala.collection.immutable.List
import scala.io.Source
import java.io.File

/**
 * File Data reader
 * @author Emiliyan Todorov
 *
 */
object DataReader {

  private val SPLIT = "\\s"
  private val TEST_RESOURCES_DIR = "./testResources"

  /**
   * @param file the file name
   * @return list of 1D points values
   */
  def readFromFile(file: String): List[Double] = {
    val dataFile = new File(TEST_RESOURCES_DIR, file)
    val values = Source.fromFile(dataFile).getLines().map(line => line.toDouble)
    values.toList
  }

  /**
   * @param file the file name
   * @return list of dimensions holding the values for each point
   */
  def readFromFileMultiD(file: String): List[List[Double]] = {
    val dataFile = new File(TEST_RESOURCES_DIR, file)
    val lines = Source.fromFile(dataFile).getLines().toList
    val points = lines.map(line => line.split(SPLIT).map(string => string.toDouble).toList)
    points.transpose
  }
}
