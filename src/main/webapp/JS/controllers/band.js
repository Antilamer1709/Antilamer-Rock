angular.module('mvcApp').controller('BandCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', 'ROLES', function ($scope, $rootScope, $routeParams, BandService, ROLES) {

    $scope.bandId = $routeParams.bandId;
    $scope.band;
    $rootScope.home = false;
    $rootScope.bands = true;


    $scope.editLink = "#/band/edit/" + $scope.bandId;

    $scope.loadBand = function () {
        var params = {};
        params.id = $routeParams.bandId;
        BandService.getBand(params, function (res) {
                if (res != undefined) {
                    $scope.band = res;
                }
            }, function (err) {
                $rootScope.error = err;
            }
        );
    };
    $scope.loadBand();

}]);