import scala.sys.process._
import java.nio.file.Paths
import java.nio.file.Files

object CLIUtils {
  private val reset = "\u001B[0m"
  private val green = "\u001B[32m"
  private val red   = "\u001B[31m"

  private def formatBoxContent(lines: Seq[String], color: String): String = {
    val maxWidth = lines.map(_.length).reduceOption(_ max _).getOrElse(0) // Safe max calculation
    val boxWidth = maxWidth + 4                                           // Padding (2 spaces on each side)

    val top    = color + "╭" + "─" * (boxWidth - 2) + "╮" + reset
    val middle = lines.map(line => color + "│ " + line.padTo(maxWidth, ' ') + " │" + reset).mkString("\n")
    val bottom = color + "╰" + "─" * (boxWidth - 2) + "╯" + reset

    (top + "\n" + middle + "\n" + bottom).stripTrailing()
  }

  def boxedText(lines: String*): String = formatBoxContent(lines, green)

  def boxedConfigs(configs: (String, String)*): String = {
    val formattedLines = configs.map { case (key, value) => s"$key: $value" }
    formatBoxContent(formattedLines, green)
  }

  def boxedError(lines: String*): String = formatBoxContent(lines, red)

  private def installNpmPackages(): Unit = {
    val command        = List("npm", "install")
    val pattern        = "found \\d+ vulnerabilities".r
    val successMessage = boxedText("Installed npm packages successfully !")
    CommandWatcher.watch(command, pattern, successMessage)
  }

  def startFrontendDevServer(projectName: String, scalaVersion: String): Unit = {
    val compiledScalajsFile = Paths.get(s"target/scala-$scalaVersion/$projectName-fastopt/main.js")

    if (Files.exists(compiledScalajsFile)) {
      installNpmPackages()
      val devCommand = Seq("npm", "run", "dev")
      val pattern    = "compiled successfully".r
      val successMessage = boxedText(
        "Web app now available on http://localhost:9876"
      )
      CommandWatcher.watch(devCommand, pattern, successMessage)
    } else {
      println(boxedError("Please run watch command first. Then run dev command in a separate terminal"))
    }

  }

  def buildFrontend(): Unit = {
    installNpmPackages()
    val buildCommand   = Seq("npm", "run", "build:prod")
    val pattern        = "webpack 5.97.1 compiled".r
    val successMessage = boxedText("Web app is available at directory 'dist'")
    CommandWatcher.watch(buildCommand, pattern, successMessage)
  }
}
