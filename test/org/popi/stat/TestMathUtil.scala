package org.popi.stat

import org.scalatest.{FunSuite, Matchers}
import MathUtil._

class TestMathUtil extends FunSuite with Matchers {

  test("abs positive") {
    assert(abs(21) == 21)
    assert(abs(-21) == 21)
  }

  test("abs negative") {
    assert(abs(21) != 22)
    assert(abs(-21) != 22)
  }

  test("pow") {
    assert(pow(2, 3) == 8)
    assert(pow(3, 2) == 9)
  }

  test("sqrt") {
    assert(sqrt(9) == 3)
  }

  test("log2") {
    assert(log2(8) == 3)
  }

  test("logB") {
    assert(logB(8, 2) == 3)
  }

  test("logE") {
    logE(8) should be(2.07944 +- 0.000002)
  }
}