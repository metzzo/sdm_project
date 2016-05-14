/**
 * Created by rfischer on 13.05.16.
 */

angular.module('frontendApp')
  .factory('forwardCallService', function($http, $q, serviceURL, loginService) {
    return {
      getAssignedOperation: function(operations) {
        for (var i = 0; i < operations.length; i++) {
          var op = operations[i];
          if (op.emergencyUnitId === loginService.getUser().id) {
            return op;
          }
        }
      },
      getOperations: function() {
        return $q(function(resolve, reject) {
          $http({
            method: 'GET',
            url: serviceURL + '/forwardCallController'
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              resolve(data.operations);
            } else {
              reject();
            }
            resolve(response.data);
          }, function(response) {
            // failure
            reject();
          });
        });
      },
      finishOperation: function(operation) {
        return $q(function(resolve, reject) {
          $http({
            method: 'PUT',
            headers: {
              'Content-Type': undefined
            },
            data: operation,
            url: serviceURL + '/forwardCallController'
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              resolve(data);
            } else {
              reject();
            }
            resolve(response.data);
          }, function(response) {
            // failure
            reject();
          });
        });
      },
      takeOperation: function(operation) {
        return $q(function(resolve, reject) {
          $http({
            method: 'POST',
            headers: {
              'Content-Type': undefined
            },
            data: operation,
            url: serviceURL + '/forwardCallController'
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              resolve(data.operations);
            } else {
              reject();
            }
            resolve(response.data);
          }, function(response) {
            // failure
            reject();
          });
        });
      }
    };
  });