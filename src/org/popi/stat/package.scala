package org.popi

import scala.collection.immutable.List

package object stat {

  def toLog(xyAxis: List[(Double, Double)]): List[(Double, Double)] = {
    xyAxis.map { case (x, y) => MathUtil.log2(x) -> MathUtil.log2(y) }
  }

}
