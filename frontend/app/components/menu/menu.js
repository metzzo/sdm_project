/**
 * Created by rfischer on 23.05.16.
 */
'use strict';

angular.module('frontendApp')
  .controller('MenuCtrl', function ($scope, $location, loginService) {
    $scope.shouldDisplayForwardCall = function() {
      return loginService.getUser() && loginService.getUser().type == 'EmergencyUnit';
    };

    $scope.shouldDisplayViewLog = function() {
      return loginService.getUser();
    };

    $scope.shouldDisplayAcceptCall = function() {
      return loginService.getUser() && loginService.getUser().type == 'Dispatcher';
    };

    $scope.shouldDisplayLogin = function() {
      return !loginService.getUser();
    };

    $scope.shouldDisplayManageReport = function() {
      return loginService.getUser() && loginService.getUser().type == 'EmergencyUnit';
    };

    $scope.getClass = function (path) {
      return ($location.path().substr(0, path.length) === path) ? 'active' : '';
    };
  });
