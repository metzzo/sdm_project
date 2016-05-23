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
  .module('frontendApp', ['angular-loading-bar', 'angular-sha1', 'ngRoute'])
  .constant('serviceURL', 'http://localhost:8080/backend_war_exploded')
  .config(function($httpProvider, $routeProvider, cfpLoadingBarProvider) {
    $httpProvider.defaults.withCredentials = true;
    cfpLoadingBarProvider.includeSpinner = false;
    cfpLoadingBarProvider.includeBar = true;
    
    $routeProvider.
      when('/login', {
        templateUrl: 'components/login/login.html',
        controller: 'LoginCtrl'
      }).
      when('/acceptCall', {
        templateUrl: 'components/acceptCall/acceptCall.html',
        controller: 'AcceptCallCtrl'
      }).
      when('/forwardCall', {
        templateUrl: 'components/forwardCall/forwardCall.html',
        controller: 'ForwardCallCtrl'
      }).
      when('/manageReport', {
        templateUrl: 'components/manageReport/manageReport.html',
        controller: 'ManageReportCtrl'
      }).
      when('/manageReport/:reportId', {
        templateUrl: 'components/manageReport/updateReport.html',
        controller: 'UpdateReportCtrl'
      }).
      when('/viewLog', {
        templateUrl: 'components/viewLog/viewLog.html',
        controller: 'ViewLogCtrl'
      }).
      otherwise({
        redirectTo: '/login'
      });
  });
