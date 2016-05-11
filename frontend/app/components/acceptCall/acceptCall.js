/**
 * Created by rfischer on 11.05.16.
 */

angular.module('frontendApp')
  .controller('AcceptCallCtrl', function ($scope, loginService, acceptCallService) {
    $scope.shouldDisplayAcceptCall = function() {
      return loginService.getUser() && loginService.getUser().type == 'Dispatcher';
    };
    
    $scope.priority = 1;
    $scope.country = 'RO';
    
    $scope.accept = function() {
      acceptCallService.acceptCall($scope.type, $scope.priority, $scope.street, $scope.nr, $scope.country, $scope.postalcode, $scope.city, $scope.who, $scope.what, $scope.additionalInfo)
        .then(function() {
          // success
          console.log("success");
        }, function() {
          // fail
          console.log("fail");
        });
    };
  });
