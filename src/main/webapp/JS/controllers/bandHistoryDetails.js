angular.module('mvcApp').controller('BandHistoryDetails', ['$scope', '$rootScope', '$routeParams', 'BandService', 'ROLES', function ($scope, $rootScope, $routeParams, BandService, ROLES) {

    $scope.bandId = $routeParams.bandId;
    $scope.versionId = $routeParams.versionId;
    $scope.band;
    $rootScope.home = false;
    $rootScope.bands = true;


    $scope.editLink = "#/band/edit/" + $scope.bandId;
    $scope.historyLink = "#/band/history/" + $scope.bandId;

    $scope.loadBandVersion = function () {
        var params = {};
        params.id = $routeParams.bandId;
        params.versionId = $routeParams.versionId;
        BandService.getBandVersion(params, function (res) {
                if (res != undefined) {
                    $scope.band = res;
                    document.getElementById("bandContent").innerHTML = $scope.band.bandContent;
                }
            }, function (err) {
                $rootScope.error = err;
            }
        );
    };
    $scope.loadBandVersion();

    $scope.makeVersionCurrent = function () {
        var params = {};
        params.id = $routeParams.bandId;
        params.versionId = $routeParams.versionId;
        BandService.makeVersionCurrent(params, function (res) {
                if (res != undefined) {

                }
            }, function (err) {
                $rootScope.error = err;
            }
        );
    }

}]);