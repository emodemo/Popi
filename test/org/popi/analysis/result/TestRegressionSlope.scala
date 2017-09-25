package org.popi.analysis.result

import org.junit.Test
import scala.collection.immutable.List
import org.junit.Assert
import org.hamcrest.CoreMatchers

class TestRegressionSlope {

  @Test
  def createNonLogarithmic = {
     val inputData = List((1.0,1.0),(2.0,2.0),(3.5,4.0),(4.0,3.5),(5.0,5.0),(6.0,6.0))
     val regSlope = RegressionSlope(RegressionSlopeType.NonLogarithmic, inputData)

     Assert.assertEquals(0.98547, regSlope.slope, 0.00005)
     Assert.assertEquals(0.23577, regSlope.slopeCI, 0.00005)
     Assert.assertEquals(0.05205, regSlope.intercept, 0.00005)
     Assert.assertEquals(0.98547, regSlope.r, 0.00005)
     Assert.assertEquals(0.97115, regSlope.rSquare, 0.00005)
     Assert.assertEquals(0.49636, regSlope.ssr, 0.00005)
     Assert.assertEquals(0.00031, regSlope.significance, 0.00005)

     Assert.assertThat(inputData, CoreMatchers.is(regSlope.regData))
  }

  @Test
  def createLogarithmic = {
     val inputData = List((1.0,1.0),(2.0,2.0),(3.5,4.0),(4.0,3.5),(5.0,5.0),(6.0,6.0))
     val regSlope = RegressionSlope(RegressionSlopeType.Logarithmic, inputData)

     Assert.assertEquals(0.99195, regSlope.slope, 0.00005)
     Assert.assertEquals(0.17575, regSlope.slopeCI, 0.00005)
     Assert.assertEquals(0.01302, regSlope.intercept, 0.00005)
     Assert.assertEquals(0.99195, regSlope.r, 0.00005)
     Assert.assertEquals(0.98397, regSlope.rSquare, 0.00005)
     Assert.assertEquals(0.07395, regSlope.ssr, 0.00005)
     Assert.assertEquals(0.00009, regSlope.significance, 0.00005)

     val logData = List((0.0,0.0),(1.0,1.0),(1.8073549220576042,2.0),(2.0,1.8073549220576042),(2.321928094887362,2.321928094887362),(2.584962500721156,2.584962500721156))
     Assert.assertThat(logData, CoreMatchers.is(regSlope.regData))
  }

}