angular.module('mvcApp').controller('EditBandCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', '$window', function ($scope, $rootScope, $routeParams, BandService, $window) {

    $scope.bandId = $routeParams.bandId;
    $rootScope.home = false;
    $rootScope.bands = true;
    $scope.band;
    $scope.returnLink = "#/band/" + $scope.bandId;

    CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
    CKEDITOR.config.height = "500";

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

    $scope.saveBand = function () {
        $scope.band.id = $routeParams.bandId;
        BandService.saveBand($scope.band, function (res) {
            $window.location.href = $scope.returnLink;
        }, function (err) {
            $rootScope.error = err;
        })
    };

    $scope.canlcelEditing = function () {
        $window.location.href = $scope.returnLink;
    };

}]);