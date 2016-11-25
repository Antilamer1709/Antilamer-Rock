angular.module('mvcApp').controller('EditBandCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', '$window', function ($scope, $rootScope, $routeParams, BandService, $window) {

    $scope.bandId = $routeParams.bandId;
    $rootScope.home = false;
    $rootScope.bands = true;
    $scope.band = {};
    $scope.band.bandContent = "";
    $scope.saveContent = "";
    $scope.returnLink = "#/band/" + $scope.bandId;

    CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
    CKEDITOR.config.entities = false;
    CKEDITOR.config.height = "600";

    var count = 0; //TODO fix it
    $scope.loadBand = function () {
        if (count < 1){
            count++;
            var params = {};
            params.id = $routeParams.bandId;
            BandService.getBand(params, function (res) {
                    if (res != undefined) {
                        $scope.loadBand();
                        $scope.band = res;
                        $scope.saveContent = $scope.band.bandContent;
                        CKEDITOR.instances['contentEditor'].setData($scope.band.bandContent);
                    }
                }, function (err) {
                    $rootScope.error = err;
                }
            );
        } else {
            $scope.band.bandContent = $scope.saveContent;
        }

    };
    $scope.loadBand();

    $scope.saveBand = function () {
        $scope.band.id = $routeParams.bandId;
        BandService.saveBand($scope.band, function (res) {
            $scope.band.bandContent = CKEDITOR.instances['contentEditor'].getData();
            $window.location.href = $scope.returnLink;
        }, function (err) {
            $rootScope.error = err;
        })
    };

    $scope.canlcelEditing = function () {
        $window.location.href = $scope.returnLink;
    };

}]);