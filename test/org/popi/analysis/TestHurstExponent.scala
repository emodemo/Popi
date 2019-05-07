package org.popi.analysis

import scala.collection.immutable.List
import org.popi.DataReader
import org.scalatest.{FunSuite, Matchers}

import scala.util.Random

class TestHurstExponent extends FunSuite with Matchers {

  test("Hurst exponent") {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val result = HurstExponent.hurstExponent(data)
    result.slope should be(0.64 +- 0.005)
    result.intercept should be(-0.54 +- 0.005)
    result.r should be(0.995 +- 0.005)
  }

  // shuffling should CHANGE results on tested scales
  test("Hurst exponent shuffle") {
    val data = DataReader.readFromFile("DowJonesIndex1024days.txt")
    val dataShuffle = Random.shuffle(data)
    val result = HurstExponent.hurstExponent(dataShuffle)
    result.slope should not be (0.64 +- 0.005)
    result.intercept should not be (-0.54 +- 0.005)
  }

  test("rescaled range") {
    val data = List(1.0, 2.0, 3.0, 1.0, 1.0, 4.0)
    val result = HurstExponent.rescaledRange(data)
    result should be(1.73205 +- 0.00005)
  }
}
