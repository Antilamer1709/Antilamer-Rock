angular.module('mvcApp').controller('BandHistoryDetails', ['$scope', '$rootScope', '$routeParams', 'BandService', '$window', 'CommonService', function ($scope, $rootScope, $routeParams, BandService, $window, CommonService) {

    $scope.bandId = $routeParams.bandId;
    $scope.versionId = $routeParams.versionId;
    $scope.band;
    $rootScope.home = false;
    $rootScope.bands = true;
    $rootScope.chat = false;


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
                CommonService.showToast('success', 'Current version has been changed');
                $window.location.href = "#/band/" + $scope.bandId;
            }, function (err) {
                $rootScope.error = err;
            }
        );
    }

}]);