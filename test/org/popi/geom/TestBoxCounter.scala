package org.popi.geom

import org.junit.Test
import scala.collection.immutable.List
import org.junit.Assert

class TestBoxCounter {

  @Test
  def countBoxes = {
    val point1 = new NDimensionalPoint(List(1L,1L,1L))
    val point2 = new NDimensionalPoint(List(2L,2L,2L))
    val point3 = new NDimensionalPoint(List(3L,3L,3L))
    val point4 = new NDimensionalPoint(List(4L,4L,4L))
    val point5 = new NDimensionalPoint(List(5L,5L,5L))
    val point6 = new NDimensionalPoint(List(6L,6L,6L))
    val point7 = new NDimensionalPoint(List(7L,7L,7L))
    val point8 = new NDimensionalPoint(List(8L,8L,8L))

    val points = List(point1, point2, point3, point4, point5, point6, point7, point8)

    // box sizes coordinates are start-inclusive end-exclusive
    Assert.assertEquals(8, BoxCounter.countBoxes(points, 1).size)
    Assert.assertEquals(5, BoxCounter.countBoxes(points, 2).size)
    Assert.assertEquals(3, BoxCounter.countBoxes(points, 4).size)
    Assert.assertEquals(2, BoxCounter.countBoxes(points, 8).size)
  }
}
