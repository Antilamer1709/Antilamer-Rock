/**
 * Created by antilamer on 2016.08.04..
 */
angular.module('mvcApp').controller('IndexCtrl', ['$scope', '$rootScope', 'UserService', '$location', function ($scope, $rootScope, UserService, $location) {

    $rootScope.home = true;
    $rootScope.bands = false;
    $scope.active = "active";
    // $rootScope.user = UserService.getLoggedUser();
    // $rootScope.user = UserService.getLoggedUser();

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
    };

    $scope.getLoggerUser = function () {
        UserService.getLoggedUser(function (res) {
            $rootScope.user = res;
        }, function (err) {
            // $rootScope.user = err;
            $rootScope.error = err;
        })
    }

    $scope.getLoggerUser();

    // function getLoggedUser() {
    //     var ie = "ie";
    //     $http.post(serviceUrl + 'users/currentUser', ie).success(function (user) {
    //         user.logged = true;
    //         changeUser(user);
    //     }).error(function () {
    //         changeUser({login: '', logged: false});
    //     });
    //
    // }

    console.log($rootScope.user);
}]);