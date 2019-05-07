package org.popi.analysis

import org.popi.DataReader
import org.scalatest.{FunSuite, Matchers}

class TestGeneralizedHurstExponent extends FunSuite with Matchers {

  // for q = 1, H(q) ≈ Hurst exponent
  test("generalized Hurst Exponent, q = 1") {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val hurstExponent = GeneralizedHurstExponent.generalizedHurstExponent(data, 1)
    hurstExponent._1 should be (0.61 +- 0.005)
  }

  // for q = 2, H(q) ≈ autocorrelation function
  test("generalized Hurst Exponent, q = 2") {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val hurstExponent = GeneralizedHurstExponent.generalizedHurstExponent(data, 2)
    hurstExponent._1 should be (0.58 +- 0.005)
  }

}
