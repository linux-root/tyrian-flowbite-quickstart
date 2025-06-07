const scalaVersion = "3.6.2"
const projectName = "tyrian-flowbite-quickstart"
if (import.meta.env.DEV) {
  import(`./target/scala-${scalaVersion}/${projectName}-fastopt/main.js`)
}
else {
  import(`./target/scala-${scalaVersion}/${projectName}-opt/main.js`)
}
