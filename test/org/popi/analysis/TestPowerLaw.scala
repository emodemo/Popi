package org.popi.analysis

import org.popi.DataReader
import org.scalatest.{FunSuite, Matchers}
import scala.util.Random

// the power law (alpha) data is generated with matlab formula x = (1-rand(10000,1)).^(-1/(2.5-1));
// example: alpha is 1.5 = 2.5 - 1
class TestPowerLaw extends FunSuite with Matchers {

  test("alpha 0.5") {
    val data = DataReader.readFromFile("alpha0.5N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    slope.slope should be (-0.5 +- 0.05)
  }

  // shuffling should NOT CHANGE results on tested scales
  test("alpha 0.5 shuffled") {
    val data = DataReader.readFromFile("alpha0.5N1000.txt")
    val dataShuffle = Random.shuffle(data)
    val slope = PowerLaw.powerLawExponent(dataShuffle)
    slope.slope should be (-0.5 +- 0.05)
  }

  test("alpha 1.0") {
    val data = DataReader.readFromFile("alpha1N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    slope.slope should be (-1.0 +- 0.05)
  }

  test("alpha 1.5") {
    val data = DataReader.readFromFile("alpha1.5N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    slope.slope should be (-1.5 +- 0.05)
  }

  test("alpha 2.0") {
    val data = DataReader.readFromFile("alpha2N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    slope.slope should be (-2.0 +- 0.05)
  }

  test("alpha 2.5") {
    val data = DataReader.readFromFile("alpha2.5N1000.txt")
    val slope = PowerLaw.powerLawExponent(data)
    slope.slope should be(-2.4 +- 0.05)
  }
}
