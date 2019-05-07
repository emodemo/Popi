package org.popi.tools

import scala.collection.immutable.{List, Map}
import org.scalatest.{FunSuite, Matchers}

class TestScaleDefiner extends FunSuite with Matchers {

  test("define scale sizes") {
    val expected = Map(1L -> 100.0, 2L -> 50.0, 4L -> 25.0, 8L -> 12.5, 16L -> 6.25, 32L -> 3.125)
    val result = ScaleDefiner.resolutionsAndSizes(100)
    result should contain allElementsOf expected
  }

  test("define scale resolutions") {
    val expected = List(1L, 2L, 4L, 8L, 16L, 32L)
    val result = ScaleDefiner.resolutions(100)
    result should contain allElementsOf expected
  }
}