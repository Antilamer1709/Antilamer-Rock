angular.module('mvcApp').factory('UserService', ['$http', '$rootScope', function ($http,  $rootScope) {
    return {
        registration: function (params, success, error) {
            $http.post('user/registerUser', params).success(function (res) {
                success(res);
            }).error(function (err) {
                error(err);
            });
        }
    }
}]);
