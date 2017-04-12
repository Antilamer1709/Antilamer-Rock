angular.module('mvcApp').factory('EncoderService', ['$http', '$rootScope', function ($http,  $rootScope) {
    return {
        encode: function (params, success, error) {
            return $http.post('encoder/encode', params).success(function (res) {
                success(res);
            }).error(error);
        }
    }
}]);
