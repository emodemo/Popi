package org.popi.analysis

import org.junit.Test
import scala.collection.immutable.List
import org.junit.Assert
import org.popi.DataReader
import scala.util.Random

class TestHurstExponent {

  @Test
  def hurstExponent = {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val result = HurstExponent.hurstExponent(data)
    Assert.assertEquals(0.64, result.slope, 0.005)
    Assert.assertEquals(-0.54, result.intercept, 0.005)
    Assert.assertEquals(0.995, result.r, 0.005)
  }

  @Test // shuffling should CHANGE results on tested scales
  def hurstExponentShuffle = {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val dataShuffle = Random.shuffle(data)
    val result = HurstExponent.hurstExponent(dataShuffle)
    Assert.assertNotEquals(0.64, result.slope, 0.005)
    Assert.assertNotEquals(-0.54, result.intercept, 0.005)
  }

  @Test
  def rescaledRange = {
    val data = List(1.0, 2.0, 3.0, 1.0, 1.0, 4.0)
    val result = HurstExponent.rescaledRange(data)
    Assert.assertEquals(1.73205, result, 0.00005)
  }
}
