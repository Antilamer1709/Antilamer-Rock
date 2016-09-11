/**
 * Created by antilamer on 2016.08.04..
 */
angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', '$location', function ($scope, $rootScope, UserService, $location) {

    $rootScope.home = true;
    $rootScope.bands = false;
    $scope.active = "active";

    $scope.submitRegistration = function () {
        UserService.registration($scope.registration, function (res) {
        }, function (err) {
            $rootScope.error = err;
        })
    };

}]);