angular.module('mvcApp').factory('BandService', ['$http', '$rootScope', 'Upload', function ($http,  $rootScope, Upload) {
    return {
        getBand: function (params, success, error) {
            return $http.post('getBand', params).success(function (res) {
                success(res);
            }).error(error);
        },
        getBandVersion: function (params, success, error) {
            return $http.post('getBandVersion', params).success(function (res) {
                success(res);
            }).error(error);
        },
        saveBand: function (params, success, error) {
            return $http.post('saveBand', params).success(function (res) {
                success(res);
            }).error(error);
        },
        makeVersionCurrent: function (params, success, error) {
            return $http.post('makeVersionCurrent', params).success(function (res) {
                success(res);
            }).error(error);
        },
        seachBandHistory: function (params, success, error) {
            return $http.post('seachBandHistory', params).success(function (res) {
                success(res);
            }).error(error);
        },
        uploadImage: function (file, bandId, success, error) {
            Upload.upload({
                url: 'uploadImage',
                method: 'POST',
                fields: {
                    'bandId': bandId,
                },
                file: file
            }).success(function (res) {
                success(res);
            }).error(function (err) {
                error(err);
            });
        }
    }
}]);
