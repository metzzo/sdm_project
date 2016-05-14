/**
 * Created by rfischer on 13.05.16.
 */

angular.module('frontendApp')
  .factory('manageReportService', function($http, $q, serviceURL, loginService) {
    return {
      getReports: function() {
        return $q(function(resolve, reject) {
          $http({
            method: 'GET',
            url: serviceURL + '/manageReportController'
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              resolve(data.reports);
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
      updateReport: function(report) {
        return $q(function(resolve, reject) {
          $http({
            method: 'POST',
            data: report,
            url: serviceURL + '/manageReportController'
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
      }
    };
  });