import { defineConfig } from 'vite';
import path from 'path';

const scalaVersion = "3.6.2"

export default defineConfig(({ command }) => {
  const commonConfig = {
    // root: '.',
    publicDir: 'public',
    resolve: {
      alias: {
        'scala-js-output': command === 'serve'
          ? path.resolve(__dirname, `./target/scala-${scalaVersion}/tyrian-flowbite-quickstart-fastopt/main.js`)
          : path.resolve(__dirname, `./target/scala-${scalaVersion}/tyrian-flowbite-quickstart-opt/main.js`),
        "resources": path.resolve(__dirname, "./src/main/resources"),
        "js": path.resolve(__dirname, "./src/main/js"),
      }
    },
    server: {
      port: 9876,
      historyApiFallback: true
    },
    build: {
      outDir: 'dist',
      // assetsDir: 'assets', // Default is 'assets'
      // sourcemap: true, // Enable for production debugging if needed
    }
  };

  if (command === 'serve') { // Development specific config
    return {
      ...commonConfig,
      // Development specific overrides if any
    };
  } else { // Production specific config (command === 'build')
    return {
      ...commonConfig,
      // Production specific overrides
      build: {
        ...commonConfig.build,
        // Add content hashing for production assets like webpack did
        rollupOptions: {
          output: {
            entryFileNames: `assets/[name].[hash].js`,
            chunkFileNames: `assets/[name].[hash].js`,
            assetFileNames: `assets/[name].[hash].[ext]`
          }
        }
      }
    };
  }
});
