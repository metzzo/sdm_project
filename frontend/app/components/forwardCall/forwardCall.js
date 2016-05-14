/**
 * Created by rfischer on 13.05.16.
 */

angular.module('frontendApp')
  .controller('ForwardCallCtrl', function ($scope, $interval, $location, loginService, forwardCallService) {
    $scope.operations = [];
    $scope.assignedOperation = null;
    
    $scope.finishOperation = function() {
      forwardCallService.finishOperation($scope.assignedOperation)
        .then(function(data) {
          console.log('success');
          $location.path('/manageReport/' + data.id);
        }, function() {
          console.log('fail');
        });
    };
    
    $scope.shouldDisplayForwardCall = function() {
      return loginService.getUser() && loginService.getUser().type == 'EmergencyUnit';
    };

    $scope.fetchOperations = function() {
      forwardCallService.getOperations()
        .then(function(data) {
          console.log('success');
          $scope.operations = data;
          $scope.assignedOperation = forwardCallService.getAssignedOperation($scope.operations);
        }, function() {
          console.log('fail');
        });
    };
    
    $scope.takeOperation = function(op) {
      forwardCallService.takeOperation(op);
    };
    
    $interval(function() {
      if ($scope.shouldDisplayForwardCall()) {
        $scope.fetchOperations();
      }
    }, 1000);
  });
