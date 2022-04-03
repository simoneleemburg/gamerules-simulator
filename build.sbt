name := "gamerules-simulator"

organization := "nl.sass"

scalaVersion := "2.13.8"

libraryDependencies +=  "org.typelevel" %% "cats-core" % "2.3.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"

scalacOptions := Seq("-encoding", "utf8",
  "-target:jvm-1.8",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-deprecation",
  "-Xlog-reflective-calls",
  "-Xlint",
  "-Ywarn-unused"
)

updateOptions := updateOptions.value.withCachedResolution(true)

coverageMinimumStmtTotal := 100

coverageFailOnMinimum:= true
