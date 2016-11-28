angular.module('mvcApp').controller('BandCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', 'ROLES', function ($scope, $rootScope, $routeParams, BandService, ROLES) {

    $scope.bandId = $routeParams.bandId;
    $scope.band;
    $rootScope.home = false;
    $rootScope.bands = true;
    $rootScope.chat = false;



    $scope.editLink = "#/band/edit/" + $scope.bandId;
    $scope.historyLink = "#/band/history/" + $scope.bandId;

    $scope.loadBand = function () {
        var params = {};
        params.id = $routeParams.bandId;
        BandService.getBand(params, function (res) {
                if (res != undefined) {
                    $scope.band = res;
                    document.getElementById("bandContent").innerHTML = $scope.band.bandContent;
                }
            }, function (err) {
                $rootScope.error = err;
            }
        );
    };
    $scope.loadBand();

}]);