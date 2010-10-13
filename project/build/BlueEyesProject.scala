import sbt._

class BlueEyesProject(info: ProjectInfo) extends ParentProject(info) {

  trait Repositories {
    val scalareleases   = "Scala Repo Releases"        at "http://scala-tools.org/repo-releases/"
    val scalasnapshots  = "Scala-tools.org Repository" at "http://scala-tools.org/repo-snapshots/"
  }

   class TestDepsProject(info: ProjectInfo) extends DefaultProject(info) {
      val scalatest  = "org.scalatest"           % "scalatest"        % "1.2"    % "test"
      val scalaspec  = "org.scala-tools.testing" % "specs"            % "1.6.1"  % "test"
      val scalacheck = "org.scala-tools.testing" % "scalacheck_2.7.7" % "1.6"    % "test"
      val mockito    = "org.mockito"             % "mockito-all"      % "1.8.4"  % "test"
  }

  // hack to work around failure of inheriting dependencies via traits
  lazy val testDeps = project("test-deps", "test-deps",  new TestDepsProject(_))

  // Blueeyes projects
  lazy val health = project("health", "health",  new HealthProject(_), testDeps)

  class HealthProject(info: ProjectInfo) extends DefaultProject(info) with Repositories {
  }
}
