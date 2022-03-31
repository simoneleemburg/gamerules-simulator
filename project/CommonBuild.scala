import sbt._

object CommonBuild extends AutoPlugin {

  object autoImport {
    val apisLibsVersion = settingKey[String]("Apis libraries version")
    val pinkLibsVersion = settingKey[String]("Pink libraries version")
    val akkaHttpVersion = settingKey[String]("Akka HTTP version")
    val jmxPrometheusJavaagentVersion = settingKey[String]("Jmx Prometheus Javaagent Version")
    val aspectjweaverVersion = settingKey[String]("Aspectjweaver Version")
  }

}