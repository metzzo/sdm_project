'use strict';

/**
 * @ngdoc overview
 * @name frontendApp
 * @description
 * # frontendApp
 *
 * Main module of the application.
 */
angular
  .module('frontendApp', ['angular-sha1'])
  .constant('serviceURL', 'http://localhost:8080/backend_war_exploded');
