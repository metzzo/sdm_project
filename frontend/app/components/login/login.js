'use strict';

angular.module('frontendApp')
  .controller('LoginCtrl', function ($scope, $location, loginService) {
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
          
          if (data.type == 'Dispatcher') {
            $location.path('/acceptCall');
          } else if (data.type == 'EmergencyUnit') {
            $location.path('/forwardCall');
          }
        }, function() {
          $scope.loginFailed = true;
        });
    };
    
    $scope.shouldDisplayLogin = function() {
      return !loginService.getUser();
    };
  });
