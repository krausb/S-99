
// run subproject tests sequentially
Global / parallelExecution := false

// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `S-99` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.scalaCheck,
        library.scalaTest,
        library.log4j,
        library.log4jCore,
        library.log4jSlf4j,
        library.disruptor,
        library.typesafeConfig,
        library.akka,
        library.akkaTest,
        library.akkaLog
      ),
      libraryDependencies ++= library.log
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val scala          = "2.11.8"
      val scalaTest      = "3.0.1"
      val scalaCheck     = "1.13.5"
      val log4j          = "2.8.1"
      val disruptor      = "3.3.0"
      val jackson        = "2.9.2"
      val typesafeConfig = "1.3.1"
      val akka       = "2.5.13"
    }

    val scalaCheck       = "org.scalacheck"           %% "scalacheck"               % Version.scalaCheck % "test, it"
    val scalaTest        = "org.scalatest"            %% "scalatest"                % Version.scalaTest % "test, it"
    val log4jCore        = "org.apache.logging.log4j" % "log4j-core"                % Version.log4j
    val log4j            = "org.apache.logging.log4j" % "log4j-api"                 % Version.log4j
    val log4jSlf4j       = "org.apache.logging.log4j" % "log4j-slf4j-impl"          % Version.log4j
    val disruptor        = "com.lmax"                 % "disruptor"                 % Version.disruptor
    val typesafeConfig   = "com.typesafe"             % "config"                    % Version.typesafeConfig

    val akka             = "com.typesafe.akka"        %% "akka-actor"               % Version.akka
    val akkaTest         = "com.typesafe.akka"        %% "akka-testkit"             % Version.akka % "test, it"
    val akkaLog          = "com.typesafe.akka"        %% "akka-slf4j"               % Version.akka

    val log = Seq(log4j, log4jCore, log4jSlf4j, disruptor)

    object GlobalExclusions {
      val logbackClassic = "ch.qos.logback"              % "logback-classic"
      val logbackCore    = "ch.qos.logback"              % "logback-core"
      val tinyLog        = "org.tinylog"                 % "tinylog"
      val log4jextras    = "log4j"                       % "apache-log4j-extras"
      val slf4jlog4j12   = "org.slf4j"                   % "slf4j-log4j12"
      val minlog         = "com.esotericsoftware.minlog" % "minlog"

      val logbackDeps   = Seq(logbackClassic, logbackCore, tinyLog)
      val log4j1deps    = Seq(log4jextras, slf4jlog4j12)
      val logExclusions = logbackDeps ++ log4j1deps :+ minlog
    }

  }

// *****************************************************************************
// Aliases
// *****************************************************************************

// SBT aliases to run multiple commands in a single call
//   Optionally add it:scalastyle if the project has integration tests
addCommandAlias(
  "styleCheck",
  "; scalafmt::test ; scalastyle ; test:scalastyle; it:scalastyle"
)

// Run tests with coverage, optionally add 'it:test' if the project has
// integration tests
addCommandAlias(
  "testCoverage",
  "; coverage ; test; it:test ; coverageReport"
)

// Alias to run all SBT commands that are connected with quality assurance
addCommandAlias(
  "qa",
  "; styleCheck ; dependencyUpdates ; testCoverage"
)


// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings: SettingsDefinition =
  buildSettings ++
    gitSettings ++
    scalastyleSettings ++
    scoverageSettings ++
    wartRemoverSettings

lazy val buildSettings =
  Seq(
    // scalaVersion from .travis.yml via sbt-travisci
    // scalaVersion := "2.12.4",
    organization := "io.streamarchitect",
    organizationName := "Bastian Kraus",
    startYear := Some(2018),
    licenses += ("GPL-3.0", url("http://www.gnu.org/licenses/gpl-3.0.en.html")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-Ywarn-unused-import"
    ),
    excludeDependencies ++= library.GlobalExclusions.logExclusions,
    Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value),
    Test / unmanagedSourceDirectories := Seq((Test / scalaSource).value),
    wartremoverWarnings in (Compile, compile) ++= Warts.unsafe,
    parallelExecution in Test := false,
    parallelExecution in IntegrationTest := false,
    fork in run := true,
    updateOptions := updateOptions.value.withGigahorse(false), // https://github.com/sbt/sbt/issues/3570
)

// -----------------------------------------------------------------------------
// git settings

lazy val gitSettings = Seq(
  git.useGitDescribe := true
)

// -----------------------------------------------------------------------------
// scalastyle settings

lazy val scalastyleSettings = Seq(
  scalastyleFailOnWarning := true
)

// -----------------------------------------------------------------------------
// scoverage settings

lazy val scoverageSettings = Seq(
  coverageMinimum := 60,
  coverageFailOnMinimum := false
)

// -----------------------------------------------------------------------------
// wartremover settings

lazy val wartRemoverSettings = Seq(
  wartremoverErrors in (Compile, compile) ++= Warts.unsafe.filterNot(_ == Wart.Any),
  wartremoverExcluded ++= sourceManaged.value.**("*.scala").get
)