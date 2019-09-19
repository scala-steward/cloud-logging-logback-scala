ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "io.github.nafg.cloudlogging"
ThisBuild / version := "0.2"

lazy val marker =
  project
    .settings(
      libraryDependencies += "io.circe" %% "circe-core" % "0.12.1",
      libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.28"
    )

lazy val appender =
  project
    .dependsOn(marker)
    .settings(
      libraryDependencies += "com.google.cloud" % "google-cloud-logging-logback" % "0.110.0-alpha",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
    )
