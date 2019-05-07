name := "Popi"

version := "0.1"

scalaVersion := "2.12.8"

// project structure
scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "test"
resourceDirectory in Test := baseDirectory.value / "testResources"

// project dependencies
libraryDependencies ++= Seq(
  // math
  "org.apache.commons" % "commons-math3" % "3.6"
  // test
  , "org.scalactic" %% "scalactic" % "3.0.5"
  , "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)