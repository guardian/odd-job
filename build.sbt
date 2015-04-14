name := "oddjob"

version := "1.0"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.1.3" % "test",
  "com.typesafe.play" %% "play-json" % "2.3.0"
)

fork in run := true