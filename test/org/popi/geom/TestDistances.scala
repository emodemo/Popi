package org.popi.geom

import org.scalatest.{FunSuite, Matchers}

import scala.collection.immutable.List

class TestDistances extends FunSuite with Matchers {

  test("euclidean distance 1D point") {
    val point1 = List[Double](1)
    val point2 = List[Double](2)
    val distance = Distances.euclidean(point1, point2)
    assert(distance == 1)
  }

  test("euclidean distance 2D point") {
    val point1 = List[Double](1, 2)
    val point2 = List[Double](2, 3)
    val distance = Distances.euclidean(point1, point2)
    distance should be (1.4142 +- 0.0002)
  }

  test("euclidean distance 3D point") {
    val point1 = List[Double](1, 3, 5)
    val point2 = List[Double](2, 4, 6)
    val distance = Distances.euclidean(point1, point2)
    distance should be (1.73205 +- 0.00005)
  }

  // TODO: additional tests for lp norm, tests for the algebraic laws
  test("NaN") {
    val point1 = List[Double](1, 3, 5)
    val point2 = List[Double](2, 4, 6)
    val distance = Distances.pNorm(0.9, point1, point2)
    assert(distance.isNaN)
  }

}