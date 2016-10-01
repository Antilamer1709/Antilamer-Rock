angular.module('mvcApp').factory('BandService', ['$http', '$rootScope', function ($http,  $rootScope) {
    return {
        getBand: function (params, success, error) {
            return $http.post('getBand', params).success(function (res) {
                success(res);
            }).error(error);
        },
        saveBand: function (params, success, error) {
            return $http.post('saveBand', params).success(function (res) {
                success(res);
            }).error(error);
        }
    }
}]);
