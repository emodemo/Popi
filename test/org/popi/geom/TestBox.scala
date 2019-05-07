package org.popi.geom

import org.scalatest.FunSuite

class TestBox extends FunSuite {

  test("equal boxes") {
    val box1 = Box(List(1L, 2L, 3L), List(1L, 2L, 3L))
    val box2 = Box(List(1L, 2L, 3L), List(1L, 2L, 3L))
    assert(box1 == box2)
  }

  test("non-equal boxes on 1st coordinate") {
    val box1 = Box(List(1L, 2L, 3L), List(1L, 2L, 3L))
    val box2 = Box(List(1L, 1L, 1L), List(1L, 2L, 3L))
    assert(box1 != box2)
  }

  test("non-equal boxes on last coordinate") {
    val box1 = Box(List(1L, 2L, 3L), List(1L, 2L, 3L))
    val box2 = Box(List(1L, 2L, 3L), List(1L, 1L, 1L))
    assert(box1 != box2)
  }

}