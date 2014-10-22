//
// http://spark.apache.org/docs/latest/quick-start.html#a-standalone-app-in-scala
//
import AssemblyKeys._

name := """spark-streaming-scala"""

scalaVersion := "2.10.4"

// When running the assembly task it is important to exclude the Spark and Hadoop dependencies
libraryDependencies ++= Dependencies.sparkAkkaHadoopProvided

// The class path needs to have provided dependencies when using the runMain task
//libraryDependencies ++= Dependencies.sparkAkkaHadoop

releaseSettings

scalariformSettings

//runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, mainClass in (Compile, runMain), runner in (Compile, runMain)) 
runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in (Compile, run))

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run)) 


assemblySettings

assembleArtifact in packageScala := false

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case "unwanted.txt"     => MergeStrategy.discard
    case x => old(x)
  }
}

test in assembly := {}

initialCommands in console := """
  |import org.apache.spark._
  |import org.apache.spark.streaming._
  |import org.apache.spark.streaming.StreamingContext._
  |import org.apache.spark.streaming.dstream._
  |import akka.actor.{ActorSystem, Props}
  |import com.typesafe.config.ConfigFactory
  |""".stripMargin


//lazy val sparkAssemblySettings = Assembly.settings ++ sbtRunSettings ++ Seq(
//	libraryDependencies ++= Dependencies.sparkAkkaHadoopProvided,
    // Redefine just the run task to use Compile deps so spark will launch correctly
//    run in Compile <<= Defaults.runTask(fullClasspath in Compile,
//                                        mainClass in (Compile, run), runner in (Compile, run)),
//    runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile,
//                                                runner in (Compile, runMain)),
//    assembleArtifact in packageScala := false   // scala-library causes problems for Spark
//)
