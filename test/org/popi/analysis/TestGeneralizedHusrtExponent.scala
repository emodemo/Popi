package org.popi.analysis

import org.junit.Test
import org.popi.DataReader
import org.junit.Assert

class TestGeneralizedHusrtExponent {

  @Test // for q = 1, H(q) ≈ Hurst exponent
  def generalizedHurstExponentForQis1 = {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val hurstExponent = GeneralizedHurstExponent.generalizedHurstExponent(data, 1)
    Assert.assertEquals(0.61, hurstExponent._1, 0.005)
  }

  @Test // for q = 2, H(q) ≈ autocorrelation function
  def generalizedHurstExponentForQis2 = {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val hurstExponent = GeneralizedHurstExponent.generalizedHurstExponent(data, 2)
    Assert.assertEquals(0.58, hurstExponent._1, 0.005)
  }

}
