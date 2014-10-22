//
// http://spark.apache.org/docs/latest/quick-start.html#a-standalone-app-in-scala
//
name := """spark-streaming-scala"""

scalaVersion := "2.10.4"

// When running the assembly task it is important to exclude the Spark and Hadoop dependencies
//libraryDependencies ++= Dependencies.sparkAkkaHadoopProvided

// The class path needs to have provided dependencies when using the runMain task
libraryDependencies ++= Dependencies.sparkAkkaHadoop

releaseSettings

scalariformSettings

initialCommands in console := """
  |import org.apache.spark._
  |import org.apache.spark.streaming._
  |import org.apache.spark.streaming.StreamingContext._
  |import org.apache.spark.streaming.dstream._
  |import akka.actor.{ActorSystem, Props}
  |import com.typesafe.config.ConfigFactory
  |""".stripMargin

//runMain in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, runMain), runner in (Compile, runMain)) 

