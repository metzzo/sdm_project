/**
 * Created by rfischer on 13.05.16.
 */

angular.module('frontendApp')
  .factory('viewLogService', function($http, $q, serviceURL, loginService) {
    return {
      getLogs: function() {
        return $q(function(resolve, reject) {
          $http({
            method: 'GET',
            url: serviceURL + '/manageLogController'
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              resolve(data.logs);
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