angular.module('mvcApp').controller('BandCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', function ($scope, $rootScope, $routeParams, BandService) {

    $scope.bandId = $routeParams.bandId;
    $scope.band;

    $scope.loadBand = function () {
        var params = {};
        params.bandId = $routeParams.bandId;
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