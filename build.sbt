import Dependencies._

lazy val ceTypeclasses = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.ikempf",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ce-typeclasses",
    scalacOptions += "-Ypartial-unification",
    libraryDependencies += cats,
    libraryDependencies += akkaHttp,
    libraryDependencies += playJson,
    libraryDependencies += scalaTest % Test
  )
