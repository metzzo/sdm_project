/**
 * Created by rfischer on 13.05.16.
 */

angular.module('frontendApp')
  .controller('ViewLogCtrl', function ($scope, $location, loginService, viewLogService) {
    $scope.logs = [];
    
    $scope.shouldDisplayViewLog = function() {
      return loginService.getUser();
    };
    
    $scope.viewReport = function(reportId) {
      $location.path('/updateReport/' + reportId);
    };
    
    viewLogService.getLogs()
      .then(function(data) {
        console.log('success');
        $scope.logs = data;
      }, function() {
        console.log('fail');
      });
  });
