package org.popi.tools

import scala.collection.immutable.List
import org.junit.Test
import org.junit.Assert

class TestDistanceMeasurer {

  @Test
  def euclideanDistance1Dpoint = {
    val point1 = List[Double](1)
    val point2 = List[Double](2)
    val distance = DistanceMeasurer.euclideanDistance(point1, point2)
    Assert.assertEquals(1, distance, 0)
  }

  @Test
  def euclideanDistance2Dpoint = {
    val point1 = List[Double](1,2)
    val point2 = List[Double](2,3)
    val distance = DistanceMeasurer.euclideanDistance(point1, point2)
    Assert.assertEquals(1.4142, distance, 0.0002)
  }

  @Test
  def euclideanDistance3Dpoint = {
    val point1 = List[Double](1,3,5)
    val point2 = List[Double](2,4,6)
    val distance = DistanceMeasurer.euclideanDistance(point1, point2)
    Assert.assertEquals(1.73205, distance, 0.00005)
  }

}