package org.popi.stat

import org.scalatest.{FunSuite, Matchers}

import scala.collection.immutable.List

class TestSimpleRegression extends FunSuite with Matchers{

  test("regression on non log data") {
    val inputData = List((1.0, 1.0), (2.0, 2.0), (3.5, 4.0), (4.0, 3.5), (5.0, 5.0), (6.0, 6.0))
    val regSlope = SimpleRegression(inputData)

    //  Assert.assertEquals(0.98547, regSlope.slope, 0.00005)
    regSlope.slope should be (0.98547 +- 0.00005)
    regSlope.slopeCI should be (0.23577 +- 0.00005)
    regSlope.intercept should be (0.05205 +- 0.00005)
    regSlope.r should be (0.98547 +- 0.00005)
    regSlope.rSquare should be (0.97115 +- 0.00005)
    regSlope.ssr should be (0.49636 +- 0.00005)
    regSlope.significance should be (0.00031 +- 0.00005)

    // Assert.assertThat(inputData, CoreMatchers.is(regSlope.regData))
    inputData should contain allElementsOf regSlope.regData
  }

  test("regression on log data") {
    val inputData = List((1.0, 1.0), (2.0, 2.0), (3.5, 4.0), (4.0, 3.5), (5.0, 5.0), (6.0, 6.0))
    val regSlope = SimpleRegression(toLog(inputData))

    regSlope.slope should be (0.99195 +- 0.00005)
    regSlope.slopeCI should be (0.17575 +- 0.00005)
    regSlope.intercept should be (0.01302 +- 0.00005)
    regSlope.r should be (0.99195 +- 0.00005)
    regSlope.rSquare should be (0.98397 +- 0.00005)
    regSlope.ssr should be (0.07395 +- 0.00005)
    regSlope.significance should be (0.00009 +- 0.00005)

    val logData = List((0.0, 0.0), (1.0, 1.0), (1.8073549220576042, 2.0), (2.0, 1.8073549220576042), (2.321928094887362, 2.321928094887362), (2.584962500721156, 2.584962500721156))
    logData should contain allElementsOf regSlope.regData
  }

}