package org.popi.analysis

import org.junit.Test
import org.junit.Assert
import org.popi.DataReader

class TestMultiFractal {

  @Test
  def multiFractalsKoch = {
    val theoreticalCapacityDim = 1.26
    val data = DataReader.readFromFileMultiD("kochCurve.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    Assert.assertEquals(theoreticalCapacityDim, mf, 0.2)
  }

  @Test
  def multiFractalsBritainCoast = {
    val theoreticalCapacityDim = 1.25
    val data = DataReader.readFromFileMultiD("britainsCoast.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    Assert.assertEquals(theoreticalCapacityDim, mf, 0.2)
  }

  @Test
  def multiFractalsHenonMap = {
    val theoreticalCapacityDim = 1.25
    val data = DataReader.readFromFileMultiD("henonMap.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    Assert.assertEquals(theoreticalCapacityDim, mf, 0.2)
  }

  @Test
  def multiFractalsCantorSet = {
    val theoreticalCapacityDim = 0.639
    val data = DataReader.readFromFileMultiD("cantorSet.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    Assert.assertEquals(theoreticalCapacityDim, mf, 0.2)
  }

  @Test
  def multiFractalsLogisticMap = {
    val theoreticalCapacityDim = 0.538
    val data = DataReader.readFromFileMultiD("logisticMap.txt")
    val mf = MultiFractal.multiFractals(data, 0)
    Assert.assertEquals(theoreticalCapacityDim, mf, 0.2)
  }

  @Test
  def multiFractalsLorenz = {
    val theoreticalCapacityDim = 1.653 // ??
    val data = DataReader.readFromFileMultiD("lorenz.dat")
    val mf = MultiFractal.multiFractals(data, 0)
    Assert.assertEquals(theoreticalCapacityDim, mf, 0.2)
  }
}
