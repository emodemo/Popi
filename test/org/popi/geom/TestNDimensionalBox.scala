package org.popi.geom

import org.junit.Test
import org.junit.Assert

class TestNDimensionalBox {

  @Test
  def boxEqualityPositive = {
    val box1 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L))
    val box2 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L))
    Assert.assertEquals(box1, box2)
  }

  @Test
  def boxEqualityPositiveDifferentNOfPoints = {
    val box1 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L), 1)
    val box2 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L), 4)
    Assert.assertEquals(box1, box2)
  }

  @Test
  def boxEqualityNegativeSize = {
    val box1 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L))
    val box2 = new NDimensionalBox(2L, List(1L,2L,3L), List(1L,2L,3L))
    Assert.assertNotEquals(box1, box2)
  }

  @Test
  def boxEqualityNegativeStartCooridnates = {
    val box1 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L))
    val box2 = new NDimensionalBox(1L, List(1L,1L,1L), List(1L,2L,3L))
    Assert.assertNotEquals(box1, box2)
  }

  @Test
  def boxEqualityNegativeEndCoordinates= {
    val box1 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L))
    val box2 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,1L,1L))
    Assert.assertNotEquals(box1, box2)
  }

  @Test
  def addAPoint = {
    val box1 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,2L,3L))
    val box2 = new NDimensionalBox(1L, List(1L,2L,3L), List(1L,1L,1L), 3)
    box1.addAPoint
    Assert.assertEquals(1, box1.getNumberOfPoints)
    box2.addAPoint
    box2.addAPoint
    Assert.assertEquals(5, box2.getNumberOfPoints)
  }

}