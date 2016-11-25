angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', '$route', '$location', '$window', 'ROLES', 'ngToast', 'CommonService', function ($scope, $rootScope, UserService, $route, $location, $window, ROLES, ngToast, CommonService) {

    $rootScope.block = false;
    $rootScope.home = true;
    $rootScope.bands = false;
    $scope.active = "active";
    $scope.user = {};
    $scope.user.username = "";
    $scope.user.logged = false;
    $scope.login = {};
    $scope.password = "";


    ngToast.settings.horizontalPosition = 'center';

    $scope.submitRegistration = function () {
        if (!$rootScope.block) {
            $rootScope.block = true;
            UserService.registration($scope.registration, function (res) {
                CommonService.showToast('success', 'Registration success');
                $scope.login.username = $scope.registration.username;
                $scope.login.password = $scope.registration.password;
                $rootScope.block = false;
                $scope.submitLogin();
            }, function (err) {
                $rootScope.block = false;
                $rootScope.error = err;
            })
        }
    };
    $scope.submitLogin = function () {
        if (!$rootScope.block) {
            $rootScope.block = true;
            UserService.login($scope.login, function (res) {
                CommonService.showToast('success', 'You are logged');
                $scope.getLoggerUser();
                $rootScope.block = false;
            }, function (err) {
                $rootScope.block = false;
                $rootScope.error = err;
            })
        }
    };


    $scope.getLoggerUser = function () {
        UserService.getLoggedUser(function (res) {
            $rootScope.user = res;
            $scope.user.username = res.username;
            $scope.user.logged = res.logged;
            $rootScope.user.role = res.role;
            $scope.isAdmin = $rootScope.user.role == ROLES.ADMIN || $rootScope.user.role == ROLES.SUPER_ADMIN;
        }, function (err) {
            $rootScope.error = err;
        })
    }
    $scope.getLoggerUser();
}]);