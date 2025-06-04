import { defineConfig } from 'vite';
import path from 'path';

export default defineConfig(({ command }) => {
  const commonConfig = {
    // Assuming index.html will be at the project root
    // root: '.',
    publicDir: 'public',
    resolve: {
      alias: {
        // Alias to help import Scala.js output
        // Adjust the path based on where sbt outputs the JS file relative to project root
        'scala-js-output': command === 'serve'
          ? path.resolve(__dirname, './target/scala-3.6.2/tyrian-flowbite-quickstart-fastopt/main.js')
          : path.resolve(__dirname, './target/scala-3.6.2/tyrian-flowbite-quickstart-opt/main.js'),
        // Keep existing aliases if they are still relevant
        "resources": path.resolve(__dirname, "./src/main/resources"),
        "js": path.resolve(__dirname, "./src/main/js"),
      }
    },
    server: {
      port: 9876,
      historyApiFallback: true,
      // proxy: { ... } // If you need to proxy API requests
      // https: true // Uncomment if you want to use basicSsl plugin
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
