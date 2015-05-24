organization := "com.kalmanb"
version :="0.0.1-SNAPSHOT"
name := "case-class-copy"

scalaVersion := "2.11.6"

// crossScalaVersions := Seq("2.11.0", "2.11.1", "2.11.2", "2.11.4", "2.11.5", "2.11.6")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:experimental.macros")

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-reflect" % _)

lazy val root = project.in(file(".")).dependsOn(testSpecs % "test->test")

lazy val testSpecs = RootProject(uri("git://github.com/kalmanb/scalatest-specs.git#0.1.1"))

