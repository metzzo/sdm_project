/**
 * Created by rfischer on 11.05.16.
 */

angular.module('frontendApp')
  .controller('AcceptCallCtrl', function ($scope, loginService, acceptCallService) {
    $scope.shouldDisplayAcceptCall = function() {
      return loginService.getUser() && loginService.getUser().type == 'Dispatcher';
    };
    
    function reset() {
      $scope.acceptedCall = false;

      $scope.data = {
        type: '0',
        priority: '1',
        country: 'RO',
        street: '',
        nr: '',
        city: '',
        postalcode: '',
        who: '',
        what: '',
        additionalInfo: '',
        logId: null
      };
    }
    
    reset();
    
    $scope.acceptCall = function() {
      acceptCallService.createLog()
        .then(function(data) {
          console.log("success");
          $scope.data.logId = data.id;
          $scope.acceptedCall = true;
        }, function() {
          console.log("fail");
        });
    };
    
    $scope.submitCall = function() {
      acceptCallService.acceptCall($scope.data)
        .then(function() {
          // success
          console.log("success");
          reset();
        }, function() {
          // fail
          console.log("fail");
        });
    };
  });
