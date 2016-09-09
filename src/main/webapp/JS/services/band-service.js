/**
 * Created by antilamer on 22.07.16.
 */

angular.module('mvcApp').factory('BandService', ['$http', '$rootScope', function ($http,  $rootScope) {
    return {
        getBand: function (params, success, error) {
            return $http.post('getBand', params).success(function (res) {
                success(res);
            }).error(error);
        }
    }
}]);
