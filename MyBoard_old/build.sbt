name := """MyBoard"""
organization := "jp.fpgtech"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)


scalaVersion := "2.13.5"


libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  ehcache,
  javaWs,
  "mysql" % "mysql-connector-java" % "8.0.18",
  javaJpa,
  "org.hibernate" % "hibernate-core" % "5.4.30.Final"
)

PlayKeys.externalizeResources := false