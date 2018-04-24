import Dependencies._

lazy val `ce-typeclasses` = (project in file(".")).settings(
  inThisBuild(
    List(
      addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6"),
      organization := "com.ikempf",
      scalaVersion := "2.12.4",
      version := "0.1.0-SNAPSHOT"
    )),
  name := "ce-typeclasses",
  scalacOptions += "-Ypartial-unification",
  libraryDependencies += cats,
  libraryDependencies += akkaHttp,
  libraryDependencies += playJson,
  libraryDependencies += scalaTest % Test
)
