package org.popi.geom

import scala.collection.immutable.List
import org.scalatest.FunSuite

class TestBoxCounter extends FunSuite {

  test("count Boxes") {
    val point1 = List(1L, 1L, 1L)
    val point2 = List(2L, 2L, 2L)
    val point3 = List(3L, 3L, 3L)
    val point4 = List(4L, 4L, 4L)
    val point5 = List(5L, 5L, 5L)
    val point6 = List(6L, 6L, 6L)
    val point7 = List(7L, 7L, 7L)
    val point8 = List(8L, 8L, 8L)

    val points = List(point1, point2, point3, point4, point5, point6, point7, point8)
    val scales = List(1L, 2L, 4L, 8L)
    val result = BoxCounter.countBoxes(points, scales)
    assert(result(1L).size == 8)
    assert(result(2L).size == 5)
    assert(result(4L).size == 3)
    assert(result(8L).size == 2)
  }
}
