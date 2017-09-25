package org.popi.wrapper

import org.junit.Test
import org.junit.Assert

class TestMathUtil {

  @Test
  def absPositive = {
    Assert.assertEquals(21, MathUtil.abs(21), 0)
    Assert.assertEquals(21, MathUtil.abs(-21), 0)
  }

  @Test
  def absNegative = {
    Assert.assertNotEquals(22, MathUtil.abs(21), 0)
    Assert.assertNotEquals(22, MathUtil.abs(-21), 0)
    Assert.assertNotEquals(22, MathUtil.abs(21), 0)
    Assert.assertNotEquals(22, MathUtil.abs(-21), 0)
  }

  @Test
  def pow = {
    Assert.assertEquals(8, MathUtil.pow(2, 3), 0)
    Assert.assertEquals(9, MathUtil.pow(3, 2), 0)
  }

  @Test
  def sqrt = {
      Assert.assertEquals(3, MathUtil.sqrt(9), 0)
  }

  @Test
  def log2 = {
      Assert.assertEquals(3, MathUtil.log2(8), 0)
  }

  @Test
  def logB = {
      Assert.assertEquals(3, MathUtil.logB(8, 2), 0)
  }

  @Test
  def logE = {
      Assert.assertEquals(2.07944, MathUtil.logE(8), 0.000002)
  }

}