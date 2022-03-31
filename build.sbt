name := "vending-machine"

organization in ThisBuild := "nl.zorgdomein"

scalaVersion in ThisBuild := "2.12.11"

resolvers in ThisBuild += "Zorgdomein Nexus repository" at "https://nexus.aws.zorgdomein.nl/content/groups/release-local-group/"

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

pinkLibsVersion in ThisBuild := "3.10.4"
akkaHttpVersion in ThisBuild := "10.0.13"
jmxPrometheusJavaagentVersion in ThisBuild := "0.3.1"
aspectjweaverVersion in ThisBuild := "1.8.10"

libraryDependencies in ThisBuild ++= Seq(
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
