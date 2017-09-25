package org.popi.tools

import org.junit.Test
import org.junit.Assert
import org.hamcrest.CoreMatchers
import scala.collection.immutable.List

class TestDataNormalizer {

  @Test
  def normalize = {
    val data = List[Double](1,2,3,4,5,6,7,8,9,10)
    val result = DataNormalizer.normalize(data, 10, 100)
    val expected = List[Long](10,20,30,40,50,60,70,80,90,100)
    Assert.assertThat(result, CoreMatchers.is(expected))
  }

   @Test
    def normalizeWithNegativeValues = {
    val data = List[Double](-6,11,555,321,16)
    val result = DataNormalizer.normalize(data, 0, 10000)
    Assert.assertEquals(0, result.min)
    Assert.assertEquals(10000, result.max)
  }

  @Test
  def normalizeMulitDimensionalData = {
    val data1 = List[Double](1,2,3,4,5,6,7,8,9,10)
    val data2 = List[Double](1,2,3,4,5,6,7,8,9,10)
    val data = Map(1 -> data1, 2 -> data2)
    val result = DataNormalizer.normalizeMultiD(data, 10, 100)
    val expected1 = List[Long](10,20,30,40,50,60,70,80,90,100)
    val expected2 = List[Long](10,20,30,40,50,60,70,80,90,100)
    val expected = Map(1 -> expected1, 2 -> expected2)
    Assert.assertThat(result, CoreMatchers.is(expected))
  }
}
