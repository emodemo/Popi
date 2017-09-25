package org.popi.tools

import org.junit.Test
import scala.collection.immutable.List
import org.junit.Assert
import org.hamcrest.CoreMatchers
import scala.collection.immutable.Map

class TestScaleDefiner {

  @Test
  def defineScaleSizes = {
    val expected = Map(1L -> 100.0, 2L -> 50.0, 4L -> 25.0, 8L -> 12.5, 16L -> 6.25, 32L -> 3.125)
    val result = ScaleDefiner.defineScaleSizes(100)
    Assert.assertThat(result, CoreMatchers.is(expected))
  }

  @Test
  def defineScaleResolutions = {
    val expected = List(1L,2L,4L,8L,16L,32L)
    val result = ScaleDefiner.defineScaleResolutions(100)
    Assert.assertThat(result, CoreMatchers.is(expected))
  }
}