name := "gamerules-simulator"

organization in ThisBuild := "nl.sass"

scalaVersion in ThisBuild := "2.12.11"

libraryDependencies in ThisBuild ++= Seq(
  "org.typelevel" %% "cats-core" % "2.3.0",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.mockito" % "mockito-core" % "2.+" % "test"
)

scalacOptions in ThisBuild := Seq("-encoding", "utf8",
  "-target:jvm-1.8",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-deprecation",
  "-Xlog-reflective-calls",
  "-Xlint",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ypartial-unification"
)

updateOptions in ThisBuild := updateOptions.value.withCachedResolution(true)

scalacOptions in Test ++= Seq("-Yrangepos")

coverageFailOnMinimum in ThisBuild := true
