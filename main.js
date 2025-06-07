const scalaVersion = "3.6.2"
if (import.meta.env.DEV) {
  import(`./target/scala-${scalaVersion}/tyrian-flowbite-quickstart-fastopt/main.js`)
}
else {
  import(`./target/scala-${scalaVersion}/tyrian-flowbite-quickstart-opt/main.js`)
}
