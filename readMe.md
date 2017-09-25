**POPI** is a small data analysis API focused on determining scaling properties of input data

#### <i class="icon-pencil"></i> User Guide
Users only need to use the classes inside the `org.popi.analysis` package. 
More examples and comparisons with theoretical values can be found in the tests of `org.popi.analysis`

```scala
    val pw = PowerLaw.powerLawExponentScaled(data)
    val hurst = HurstExponent.hurstExponent(data)
    val genHurst = GeneralizedHurstExponent.generalizedHurstExponent(data, 1) 
    val mf = MultiFractal.multiFractals(data, 0)
```

#### <i class="icon-pencil"></i> Test results for fractal dimension

|  Figure  	|Computed|Theoretical|
| :-------- | :----: | :-------: |
| Koch      | 1.279  |  1.26     |
| Brit coast| 1.30   |  1.25     |
| Henon map | 1.194  |  1.25     |
| Cantor set| 0.640  |  0.639    |
| Logistic  | 0.532  |  0.583    |
| Lorenz    |        |  1.653    |


#### <i class="icon-pencil"></i> Dependencies
* [Apache commons math](https://commons.apache.org/)

#### <i class="icon-pencil"></i> License
The project is under the "GNU General Public License v3.0"


**TODOs:**

> - Add approximate slopes for each scale for GeneralizedHurst and MultiFractal analyses.
> - Add multi-fractal spectrum for the MultiFractal
> - Add defining scales based on Taken's theorem
> - Add defining scales based in customer's input
> - Add defining scales based on watch time (e.g. ms, sec, min, ...)
> - Add parallel mode