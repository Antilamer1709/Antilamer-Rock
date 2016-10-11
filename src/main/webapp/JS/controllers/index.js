/**
 * Created by antilamer on 2016.08.04..
 */
angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', '$route', '$location', '$window', 'ROLES', function ($scope, $rootScope, UserService, $route, $location, $window, ROLES) {

    $rootScope.home = true;
    $rootScope.bands = false;
    $scope.active = "active";
    $scope.user = {};
    $scope.user.username = "";
    $scope.user.logged = false;
    console.log($scope.user);

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
            $rootScope.user.role = res.role;
        }, function (err) {
            $rootScope.error = err;
        })
    }

    $scope.getLoggerUser();
}]);