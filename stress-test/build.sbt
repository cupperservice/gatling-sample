import Dependencies._

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "yumemi",
      scalaVersion := "2.12.10",
      version := "0.1.0"
    )),
    name := "stress-test",
    libraryDependencies ++= gatling
  )
