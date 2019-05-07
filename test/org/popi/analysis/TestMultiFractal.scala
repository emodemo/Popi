package org.popi.analysis

import org.popi.DataReader
import org.scalatest.{FunSuite, Matchers}

class TestMultiFractal extends FunSuite with Matchers {

  test("Koch Curve") {
    val theoreticalCapacityDim = 1.26
    val data = DataReader.readFromFileMultiD("kochCurve.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    theoreticalCapacityDim should be(mf +- 0.2)
  }

  test("Britain's Coast") {
    val theoreticalCapacityDim = 1.25
    val data = DataReader.readFromFileMultiD("britainsCoast.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    theoreticalCapacityDim should be(mf +- 0.2)
  }

  test("Henon Map") {
    val theoreticalCapacityDim = 1.25
    val data = DataReader.readFromFileMultiD("henonMap.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    theoreticalCapacityDim should be(mf +- 0.2)
  }

  test("Cantor Set") {
    val theoreticalCapacityDim = 0.639
    val data = DataReader.readFromFileMultiD("cantorSet.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    theoreticalCapacityDim should be(mf +- 0.2)
  }

  test("Logistic Map") {
    val theoreticalCapacityDim = 0.538
    val data = DataReader.readFromFileMultiD("logisticMap.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    theoreticalCapacityDim should be(mf +- 0.2)
  }

  test("Lorenz") {
    val theoreticalCapacityDim = 1.653 // ??
    val data = DataReader.readFromFileMultiD("lorenz.dat")
    val mf = MultiFractal.multiFractals(data, 0)
    theoreticalCapacityDim should be(mf +- 0.2)
  }
}
