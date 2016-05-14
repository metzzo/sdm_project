/**
 * Created by rfischer on 14.05.16.
 */

angular.module('frontendApp')
  .controller('UpdateReportCtrl', function ($scope, $interval, $routeParams, $location, loginService, manageReportService) {
    $scope.data = {};
    
    manageReportService.getReports()
      .then(function(reports) {
        console.log('success');
        for (var i = 0; i < reports.length; i++) {
          var report = reports[i];
          if (report.id === +$routeParams.reportId) {
            $scope.data = report;
            break;
          }
        }
      }, function() {
        console.log('fail');
      });
    
    $scope.submitReport = function() {
      manageReportService.updateReport($scope.data)
        .then(function() {
          console.log('success');
          $location.path('/manageReport');
        }, function() {
          console.log('fail');
          
        });
    };

    $scope.shouldDisplayUpdateReport = function() {
      return loginService.getUser();
    };
    
    $scope.shouldEditReport = function() {
      return loginService.getUser().type == 'EmergencyUnit'
    };
  });
