name := """scala-interview"""

lazy val commonSettings = Seq(
  organization := "com.particeep",
  version := "1.1.0",
  scalaVersion := "2.13.5",
  resolvers ++= Seq(
    "bitbucket-release" at "https://bitbucket.org/Adrien/particeep-repository/raw/master/repository/",
    "Bintray_DL" at "https://dl.bintray.com/kamon-io/releases/",
    "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    "Kaliber Internal Repository" at "https://jars.kaliber.io/artifactory/libs-release-local",
    Resolver.jcenterRepo,
    Resolver.mavenCentral
  ),
  // libraryDependencies ++= (deps_common ++ deps_tests ++ deps_akka ++ deps_db ++ deps_graqphql),
  // scalacOptions ++= compiler_option,
  // routesGenerator := InjectedRoutesGenerator,
  updateOptions := updateOptions.value.withCachedResolution(true),
  sources in (Compile, doc) := Seq.empty
)

lazy val root: Project = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)


lazy val deps_tests = Seq(
  "org.scalatestplus"       %% "mockito-3-4"        % "3.2.6.0" % Test withSources (),
  "org.scalatestplus.play"  %% "scalatestplus-play" % "5.1.0"   % Test withSources () excludeAll (ExclusionRule(
    organization = "org.mockito"
  )),
  "com.opentable.components" % "otj-pg-embedded"    % "0.13.3"  % Test withSources ()
)


addCommandAlias("fmt", "; scalafixAll RemoveUnused; scalafixAll SortImports; all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "; scalafixAll --check; all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

inThisBuild(
  List(
    semanticdbEnabled := true,                        // enable SemanticDB
    semanticdbVersion := scalafixSemanticdb.revision, // use Scalafix compatible version
    scalafmtOnCompile := true
  )
)

scalafixDependencies in ThisBuild ++= Seq(
  "com.nequissimus" %% "sort-imports" % "0.5.5"
)
