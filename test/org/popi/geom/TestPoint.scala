package org.popi.geom

import org.scalatest.FunSuite

class TestPoint extends FunSuite {

  test("equal points") {
    val point1 = Point(List(1, 2, 3, 2, 1))
    val point2 = Point(List(1, 2, 3, 2, 1))
    assert(point1 == point2)
  }

  test("not equal points") {
    val point1 = Point(List(1, 2, 3, 2, 1))
    val point2 = Point(List(1, 2, 3, 4, 5))
    assert(point1 != point2)
  }

  test("not equal points different size") {
    val point1 = Point(List(1, 2, 3, 2, 1))
    val point2 = Point(List(1, 2, 3, 4))
    assert(point1 != point2)
  }
}