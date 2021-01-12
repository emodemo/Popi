# POPI

**POPI** is a small data analysis API focused on determining scaling properties of input data

[![Build Status](https://travis-ci.org/emodemo/Popi.svg?branch=master)](https://travis-ci.org/emodemo/Popi) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/bb68bbf838ac4afba2f50155867654c4)](https://www.codacy.com/app/emodemo/Popi?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=emodemo/Popi&amp;utm_campaign=Badge_Grade)

## User Guide

Users only need to use the classes inside the `org.popi.analysis` package.
More examples and comparisons with theoretical values can be found in the tests of `org.popi.analysis`

```scala
val pw = PowerLaw.powerLawExponent(data)
val hurst = HurstExponent.hurstExponent(data)
val genHurst = GeneralizedHurstExponent.generalizedHurstExponent(data, 1)
val mf = MultiFractal.multiFractals(data, 0)
```

## Test results for fractal dimension

|  Figure   |Computed|Theoretical|
| :-------- | :----: | :-------: |
| Koch      | 1.279  |  1.26     |
| Brit coast| 1.30   |  1.25     |
| Henon map | 1.194  |  1.25     |
| Cantor set| 0.640  |  0.639    |
| Logistic  | 0.532  |  0.583    |
| Lorenz    |        |  1.653    |

## Dependencies

* [Apache commons math](https://commons.apache.org/)

## License

The project is under the "GNU General Public License v3.0"

## TODO

* Add approximate slopes for each scale for GeneralizedHurst and MultiFractal analyses.
* Add multi-fractal spectrum for the MultiFractal
* Add defining scales based on Taken's theorem
* Add defining scales based in customer's input
* Add defining scales based on watch time (e.g. ms, sec, min, ...)
* Conditional / Joint implementations
