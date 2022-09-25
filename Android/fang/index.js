/**
 * @format
 */

import {AppRegistry} from 'react-native';
//import App from './App';
import App from './source/js/reg.js';

import {name as appName} from './app.json';

AppRegistry.registerComponent(appName, () => App);
