import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest"     %% "scalatest" % "3.0.5"
  lazy val akkaHttp  = "com.typesafe.akka" %% "akka-http" % "10.1.0"
  lazy val playJson  = "com.typesafe.play" %% "play-json" % "2.6.7"
  lazy val cats      = "org.typelevel"     %% "cats-core" % "1.1.0"
}
