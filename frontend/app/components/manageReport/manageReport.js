/**
 * Created by rfischer on 13.05.16.
 */

angular.module('frontendApp')
  .controller('ManageReportCtrl', function ($scope, $location, loginService, manageReportService) {
    $scope.reports = [];
    
    manageReportService.getReports()
      .then(function(data) {
        console.log('success');
        $scope.reports = data;
      }, function() {
        console.log('fail');
        
      });
    
    $scope.updateReport = function(report) {
      $location.path('/manageReport/' + report.id);
    };
    
    $scope.shouldDisplayManageReport = function() {
      return loginService.getUser() && loginService.getUser().type == 'EmergencyUnit';
    };
  });
