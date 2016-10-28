/**
 * Created by antilamer on 2016.08.04..
 */
angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', '$route', '$location', '$window', 'ROLES', 'ngToast', function ($scope, $rootScope, UserService, $route, $location, $window, ROLES, ngToast) {

    $rootScope.home = true;
    $rootScope.bands = false;
    $scope.active = "active";
    $scope.user = {};
    $scope.user.username = "";
    $scope.user.logged = false;


    ngToast.settings.horizontalPosition = 'center'
    // ngToast.configure({
    //     verticalPosition: 'bottom',
    //     horizontalPosition: 'center',
    //     maxNumber: 3
    // });

    $scope.submitRegistration = function () {
        UserService.registration($scope.registration, function (res) {
            $scope.getLoggerUser();
        }, function (err) {
            $rootScope.error = err;
        })
    };

    $scope.submitLogin = function () {
        UserService.login($scope.login, function (res) {
            $scope.getLoggerUser();
            ngToast.create({
                className: 'success',
                horizontalPosition: 'left',
                content: '<a href="#" class="">new</a>'
            });
        }, function (err) {
            $rootScope.error = err;
        })
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