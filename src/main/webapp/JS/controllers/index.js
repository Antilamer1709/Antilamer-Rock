/**
 * Created by antilamer on 2016.08.04..
 */
angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', '$route', '$location', '$window', function ($scope, $rootScope, UserService, $route, $location, $window) {

    $rootScope.home = true;
    $rootScope.bands = false;
    $scope.active = "active";
    $scope.user = {};
    $scope.user.username = "";
    $scope.user.logged = false;

    $scope.submitRegistration = function () {
        UserService.registration($scope.registration, function (res) {
        }, function (err) {
            $rootScope.error = err;
        })
    };

    $scope.submitLogin = function () {
        UserService.login($scope.login, function (res) {
        }, function (err) {
            $rootScope.error = err;
        })
        $window.location.href = $location.$$path;
    };

    $scope.getLoggerUser = function () {
        UserService.getLoggedUser(function (res) {
            $rootScope.user = res;
            $scope.user.username = res.username;
            $scope.user.logged = res.logged;
        }, function (err) {
            $rootScope.error = err;
        })
    }

    $scope.getLoggerUser();
}]);