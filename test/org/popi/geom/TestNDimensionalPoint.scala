package org.popi.geom

import org.junit.Test
import org.junit.Assert

class TestNDimensionalPoint {

  @Test
  def pointEqualityPositive = {
    val point1 = new NDimensionalPoint(List(1,2,3,2,1))
    val point2 = new NDimensionalPoint(List(1,2,3,2,1))
    Assert.assertEquals(point1, point2)
  }

  @Test
  def pointEqualityNegative = {
    val point1 = new NDimensionalPoint(List(1,2,3,2,1))
    val point2 = new NDimensionalPoint(List(1,2,3,4,5))
    Assert.assertNotEquals(point1, point2)
  }

}