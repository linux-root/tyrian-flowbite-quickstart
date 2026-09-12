import { defineConfig } from 'vite';
import path from 'path';
import tailwindcss from '@tailwindcss/vite';
const scalaVersion = '3.9.0'
const scalaProjectName = 'tyrian-flowbite-quickstart'

export default defineConfig(({ command }) => {
  return {
    plugins: [tailwindcss()],
    resolve: {
      alias: {
        "scalajs": command == "serve" ? `./target/scala-${scalaVersion}/${scalaProjectName}-fastopt/main.js`: `./target/scala-${scalaVersion}/${scalaProjectName}-opt/main.js`,
        "resources": path.resolve(__dirname, "./src/main/resources"),
        "js": path.resolve(__dirname, "./src/main/js"),
      }
    },
    server: {
      port: 9876,
      historyApiFallback: true
    },
  };
});
