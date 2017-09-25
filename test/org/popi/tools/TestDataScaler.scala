package org.popi.tools

import org.junit.Test
import scala.collection.immutable.List
import org.junit.Assert
import org.hamcrest.CoreMatchers
import scala.collection.immutable.Map

class TestDataScaler {

  @Test
  def scaleBySize = {
    val data = List(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,
        21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0,30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0,40.0,
        41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0,50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0,60.0,
        61.0,62.0,63.0,64.0,65.0,66.0,67.0,68.0,69.0,70.0,71.0,72.0,73.0,74.0,75.0,76.0,77.0,78.0,79.0,80.0,
        81.0,82.0,83.0,84.0,85.0,86.0,87.0,88.0,89.0,90.0,91.0,92.0,93.0,94.0,95.0,96.0,97.0,98.0,99.0,100)

    val expectedScale1 = List(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100))
    val expectedScale2 = List(List(51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100),
        List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50))
    val expectedScale4 = List(List(76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100),
        List(51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75),
        List(26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50),
        List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25))
    val expectedScale8 = List(
        List(85.0, 86.0, 87.0, 88.0, 89.0, 90.0, 91.0, 92.0, 93.0, 94.0, 95.0, 96.0),
        List(73.0, 74.0, 75.0, 76.0, 77.0, 78.0, 79.0, 80.0, 81.0, 82.0, 83.0, 84.0),
        List(61.0, 62.0, 63.0, 64.0, 65.0, 66.0, 67.0, 68.0, 69.0, 70.0, 71.0, 72.0),
        List(49.0, 50.0, 51.0, 52.0, 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0),
        List(37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0, 48.0),
        List(25.0, 26.0, 27.0, 28.0, 29.0, 30.0, 31.0, 32.0, 33.0, 34.0, 35.0, 36.0),
        List(13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0),
        List(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0))
    val expectedScale16 = List(
        List(91.0, 92.0, 93.0, 94.0, 95.0, 96.0), List(85.0, 86.0, 87.0, 88.0, 89.0, 90.0),
        List(79.0, 80.0, 81.0, 82.0, 83.0, 84.0), List(73.0, 74.0, 75.0, 76.0, 77.0, 78.0),
        List(67.0, 68.0, 69.0, 70.0, 71.0, 72.0), List(61.0, 62.0, 63.0, 64.0, 65.0, 66.0),
        List(55.0, 56.0, 57.0, 58.0, 59.0, 60.0), List(49.0, 50.0, 51.0, 52.0, 53.0, 54.0),
        List(43.0, 44.0, 45.0, 46.0, 47.0, 48.0), List(37.0, 38.0, 39.0, 40.0, 41.0, 42.0),
        List(31.0, 32.0, 33.0, 34.0, 35.0, 36.0), List(25.0, 26.0, 27.0, 28.0, 29.0, 30.0),
        List(19.0, 20.0, 21.0, 22.0, 23.0, 24.0), List(13.0, 14.0, 15.0, 16.0, 17.0, 18.0),
        List(7.0, 8.0, 9.0, 10.0, 11.0, 12.0), List(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
    val expected = Map(100.0 -> expectedScale1, 50.0 -> expectedScale2, 25.0 -> expectedScale4, 12.5 -> expectedScale8, 6.25 -> expectedScale16)

    val scaleSizes = ScaleDefiner.defineScaleSizes(data.size);
    val result = DataScaler.scaleBySize(data, scaleSizes)

    Assert.assertThat(result, CoreMatchers.is(expected))
  }

  @Test
  def scaleByDeltaResolution = {
    val data = List(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,
        21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0,30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0,40.0,
        41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0,50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0,60.0,
        61.0,62.0,63.0,64.0,65.0,66.0,67.0,68.0,69.0,70.0,71.0,72.0,73.0,74.0,75.0,76.0,77.0,78.0,79.0,80.0,
        81.0,82.0,83.0,84.0,85.0,86.0,87.0,88.0,89.0,90.0,91.0,92.0,93.0,94.0,95.0,96.0,97.0,98.0,99.0,100.0)
    val expectedScale1 = List(List(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 29.0, 30.0, 31.0, 32.0, 33.0, 34.0, 35.0, 36.0, 37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0, 48.0, 49.0, 50.0, 51.0, 52.0, 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0, 61.0, 62.0, 63.0, 64.0, 65.0, 66.0, 67.0, 68.0, 69.0, 70.0, 71.0, 72.0, 73.0, 74.0, 75.0, 76.0, 77.0, 78.0, 79.0, 80.0, 81.0, 82.0, 83.0, 84.0, 85.0, 86.0, 87.0, 88.0, 89.0, 90.0, 91.0, 92.0, 93.0, 94.0, 95.0, 96.0, 97.0, 98.0, 99.0, 100.0))
    val expectedScale2 = List(List(1.0, 3.0, 5.0, 7.0, 9.0, 11.0, 13.0, 15.0, 17.0, 19.0, 21.0, 23.0, 25.0, 27.0, 29.0, 31.0, 33.0, 35.0, 37.0, 39.0, 41.0, 43.0, 45.0, 47.0, 49.0, 51.0, 53.0, 55.0, 57.0, 59.0, 61.0, 63.0, 65.0, 67.0, 69.0, 71.0, 73.0, 75.0, 77.0, 79.0, 81.0, 83.0, 85.0, 87.0, 89.0, 91.0, 93.0, 95.0, 97.0, 99.0),
      List(2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 24.0, 26.0, 28.0, 30.0, 32.0, 34.0, 36.0, 38.0, 40.0, 42.0, 44.0, 46.0, 48.0, 50.0, 52.0, 54.0, 56.0, 58.0, 60.0, 62.0, 64.0, 66.0, 68.0, 70.0, 72.0, 74.0, 76.0, 78.0, 80.0, 82.0, 84.0, 86.0, 88.0, 90.0, 92.0, 94.0, 96.0, 98.0, 100.0))
    val expectedScale4 = List(
      List(1.0, 5.0, 9.0, 13.0, 17.0, 21.0, 25.0, 29.0, 33.0, 37.0, 41.0, 45.0, 49.0, 53.0, 57.0, 61.0, 65.0, 69.0, 73.0, 77.0, 81.0, 85.0, 89.0, 93.0, 97.0),
      List(2.0, 6.0, 10.0, 14.0, 18.0, 22.0, 26.0, 30.0, 34.0, 38.0, 42.0, 46.0, 50.0, 54.0, 58.0, 62.0, 66.0, 70.0, 74.0, 78.0, 82.0, 86.0, 90.0, 94.0, 98.0),
      List(3.0, 7.0, 11.0, 15.0, 19.0, 23.0, 27.0, 31.0, 35.0, 39.0, 43.0, 47.0, 51.0, 55.0, 59.0, 63.0, 67.0, 71.0, 75.0, 79.0, 83.0, 87.0, 91.0, 95.0, 99.0),
      List(4.0, 8.0, 12.0, 16.0, 20.0, 24.0, 28.0, 32.0, 36.0, 40.0, 44.0, 48.0, 52.0, 56.0, 60.0, 64.0, 68.0, 72.0, 76.0, 80.0, 84.0, 88.0, 92.0, 96.0, 100.0))
    val expectedScale8 = List(
      List(1.0, 9.0, 17.0, 25.0, 33.0, 41.0, 49.0, 57.0, 65.0, 73.0, 81.0, 89.0, 97.0),
      List(2.0, 10.0, 18.0, 26.0, 34.0, 42.0, 50.0, 58.0, 66.0, 74.0, 82.0, 90.0, 98.0),
      List(3.0, 11.0, 19.0, 27.0, 35.0, 43.0, 51.0, 59.0, 67.0, 75.0, 83.0, 91.0, 99.0),
      List(4.0, 12.0, 20.0, 28.0, 36.0, 44.0, 52.0, 60.0, 68.0, 76.0, 84.0, 92.0, 100.0),
      List(5.0, 13.0, 21.0, 29.0, 37.0, 45.0, 53.0, 61.0, 69.0, 77.0, 85.0, 93.0),
      List(6.0, 14.0, 22.0, 30.0, 38.0, 46.0, 54.0, 62.0, 70.0, 78.0, 86.0, 94.0),
      List(7.0, 15.0, 23.0, 31.0, 39.0, 47.0, 55.0, 63.0, 71.0, 79.0, 87.0, 95.0),
      List(8.0, 16.0, 24.0, 32.0, 40.0, 48.0, 56.0, 64.0, 72.0, 80.0, 88.0, 96.0))
    val expectedScale16 =  List(
      List(1.0, 17.0, 33.0, 49.0, 65.0, 81.0, 97.0), List(2.0, 18.0, 34.0, 50.0, 66.0, 82.0, 98.0),
      List(3.0, 19.0, 35.0, 51.0, 67.0, 83.0, 99.0), List(4.0, 20.0, 36.0, 52.0, 68.0, 84.0, 100.0),
      List(5.0, 21.0, 37.0, 53.0, 69.0, 85.0), List(6.0, 22.0, 38.0, 54.0, 70.0, 86.0),
      List(7.0, 23.0, 39.0, 55.0, 71.0, 87.0), List(8.0, 24.0, 40.0, 56.0, 72.0, 88.0),
      List(9.0, 25.0, 41.0, 57.0, 73.0, 89.0), List(10.0, 26.0, 42.0, 58.0, 74.0, 90.0),
      List(11.0, 27.0, 43.0, 59.0, 75.0, 91.0), List(12.0, 28.0, 44.0, 60.0, 76.0, 92.0),
      List(13.0, 29.0, 45.0, 61.0, 77.0, 93.0), List(14.0, 30.0, 46.0, 62.0, 78.0, 94.0),
      List(15.0, 31.0, 47.0, 63.0, 79.0, 95.0), List(16.0, 32.0, 48.0, 64.0, 80.0, 96.0))
    val expected = Map(1.0 -> expectedScale1, 2.0 -> expectedScale2, 4.0 -> expectedScale4, 8.0 -> expectedScale8, 16.0 -> expectedScale16)

    val scaleSizes = ScaleDefiner.defineScaleSizes(data.size);
    val result = DataScaler.scaleByDeltaResolution(data, scaleSizes)
    Assert.assertEquals(expected, result)
  }
}