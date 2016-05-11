/**
 * Created by rfischer on 10.05.16.
 */


angular.module('frontendApp')
  .factory('loginService', function($http, $q, serviceURL, sha1) {
    var user = null;
    
    return {
      getUser: function() {
        return user;
      },
      logout: function() {
        // TODO
      },
      login: function(name, pw) {
        return $q(function(resolve, reject) {
          $http({
            method: 'POST',
            url: serviceURL + '/loginController',
            headers: {
              'Content-Type': undefined
            },
            data: {
              username: name,
              pw: sha1.hash(pw)
            }
          }).then(function(response) {
            // success
            var data = response.data;
            if (data.success) {
              user = data;
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