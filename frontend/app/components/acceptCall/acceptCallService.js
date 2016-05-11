/**
 * Created by rfischer on 11.05.16.
 */

angular.module('frontendApp')
  .factory('acceptCallService', function($http, $q, serviceURL) {
    return {
      acceptCall: function(type, priority, street, nr, country, postalcode, city, who, what, additionalInfo) {
        return $q(function(resolve, reject) {
          $http({
            method: 'POST',
            url: serviceURL + '/acceptCallController',
            headers: {
              'Content-Type': undefined
            },
            data: {
              type: type,
              priority: priority,
              street: street,
              nr: nr,
              country: country,
              postalcode: postalcode,
              city: city,
              who: who,
              what: what,
              additionalInfo: additionalInfo
            }
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              resolve();
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