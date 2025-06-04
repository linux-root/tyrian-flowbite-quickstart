// Import and initialize Flowbite
import { init as initFlowbite } from './flowbite.js';

initFlowbite();

// Import the Scala.js application
// This will execute the Scala.js main function, which should set up the Tyrian application.
import 'scala-js-output';

console.log('Vite main.js loaded and Scala.js app initiated.');
