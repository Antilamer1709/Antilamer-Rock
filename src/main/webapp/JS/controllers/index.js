/**
 * Created by antilamer on 2016.08.04..
 */
angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', function ($scope, $rootScope, UserService) {

    $scope.testvar = "active";
    // $scope.registration = {};

    $scope.submitRegistration = function () {
        UserService.registration($scope.registration, function (res) {
        }, function (err) {
            $rootScope.error = err;
        })
    };

}]);