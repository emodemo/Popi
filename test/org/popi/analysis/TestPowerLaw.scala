package org.popi.analysis

import org.junit.Test
import org.popi.DataReader
import org.junit.Assert
import scala.util.Random


  // the power law (alpha) data is generated with matlab formula x = (1-rand(10000,1)).^(-1/(2.5-1));
  // example: alpha is 1.5 = 2.5 - 1
class TestPowerLaw {

  @Test
  def powerLawExponentScaled = {
    val data = DataReader.readFromFile("alpha0.5N1000.txt")
    val slopes = PowerLaw.powerLawExponentScaled(data)
    Assert.assertEquals(8, slopes.size, 0)
    Assert.assertEquals(-0.52, slopes(1L).slope, 0.006)
  }

  @Test
  def powerLawExponent0p5 = {
    val data = DataReader.readFromFile("alpha0.5N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    Assert.assertEquals(-0.5, slope.slope, 0.05)
  }

  @Test // shuffling should NOT CHANGE results on tested scales
  def powerLawExponent0p5Shuffle = {
    val data = DataReader.readFromFile("alpha0.5N1000.txt")
    val dataShuffle = Random.shuffle(data)
    val slope = PowerLaw.powerLawExponent(dataShuffle)
    Assert.assertEquals(-0.5, slope.slope, 0.05)
  }

  @Test
  def powerLawExponent1p0 = {
    val data = DataReader.readFromFile("alpha1N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    Assert.assertEquals(-1, slope.slope, 0.05)
  }

  @Test
  def powerLawExponent1p5 = {
    val data = DataReader.readFromFile("alpha1.5N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    Assert.assertEquals(-1.5, slope.slope, 0.05)
  }

  @Test
  def powerLawExponent2p0 = {
    val data = DataReader.readFromFile("alpha2N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    Assert.assertEquals(-2, slope.slope, 0.05)
  }

  @Test
  def powerLawExponent2p5 = {
    val data = DataReader.readFromFile("alpha2.5N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    Assert.assertEquals(-2.4, slope.slope, 0.05)
  }
}
