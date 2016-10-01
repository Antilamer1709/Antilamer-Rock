angular.module('mvcApp').factory('UserService', ['$http', '$rootScope', function ($http,  $rootScope) {

    return {
        registration: function (params, success, error) {
            $http.post('user/registerUser', params).success(function (res) {
                success(res);
            }).error(function (err) {
                error(err);
            });
        },
        login: function (params, success, error) {
            $http.post('user/login', params).success(function (res) {
                success(res);
            }).error(function (err) {
                error(err);
            });
        }
        // ,
        // getLoggedUser: function getLoggedUser() {
        //     $http.post('user/currentUser').success(function (user) {
        //         // user.logged = true;
        //         success(user);
        //     }).error(function (err) {
        //         // user.logged = false;
        //         error(err)
        //     });
        //
        // }
        ,
        getLoggedUser: function (success, error) {
            $http.post('user/currentUser').success(function (res) {
                success(res);
            }).error(function (err) {
                error(err);
            });
        }
    }
}]);
