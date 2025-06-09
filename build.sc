import mill._
import mill.scalalib._

object diss extends ScalaModule {

  def scalaVersion = "2.13.14"
  
  def detectOs: String = ""

  def scalacOptions = Seq("-Ymacro-annotations")
  def ivyDeps = Agg(
      ivy"org.typelevel::cats-core:2.12.0",
      ivy"org.typelevel::cats-effect:3.5.7",
      ivy"tf.tofu::tofu-core-ce3:0.13.6",
      ivy"io.estatico::newtype:0.4.4",
      ivy"com.beachape::enumeratum:1.7.5"
  )
}

