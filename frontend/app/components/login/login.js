'use strict';

angular.module('frontendApp')
  .controller('LoginCtrl', function ($scope, loginService) {
    $scope.data = {
      username: "",
      pw: ""
    };
    
    $scope.loginFailed = false;

    $scope.login = function() {
      loginService
        .login($scope.data.username, $scope.data.pw)
        .then(function(data) {
          $scope.loginFailed = false;
        }, function() {
          $scope.loginFailed = true;
        });
    };
    
    $scope.shouldDisplayLogin = function() {
      return !loginService.getUser();
    }
  });
