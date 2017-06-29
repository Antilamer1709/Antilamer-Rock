angular.module('mvcApp').controller('EditBandCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', '$window', 'CommonService', function ($scope, $rootScope, $routeParams, BandService, $window, CommonService) {

    $scope.bandId = $routeParams.bandId;
    $rootScope.home = false;
    $rootScope.bands = true;
    $rootScope.chat = false;
    $scope.band = {};
    $scope.band.bandContent = "";
    $scope.saveContent = "";
    $scope.switchStatus = true;
    $scope.returnLink = "#/band/" + $scope.bandId;

    CKEDITOR.config.enterMode = CKEDITOR.ENTER_BR;
    CKEDITOR.config.entities = false;
    CKEDITOR.config.height = "600";

    var count = 0;
    $scope.loadBand = function () {
        if (count < 1){
            count++;
            var params = {};
            params.id = $routeParams.bandId;
            BandService.getBand(params, function (res) {
                    if (res != undefined) {
                        $scope.loadBand();
                        $scope.band = res;
                        if($scope.band.uploadedImage){
                            $scope.band.image = "";
                        }
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
        $scope.band.uploadedImage = $scope.switchStatus;
        BandService.saveBand($scope.band, function (res) {
            $scope.band.bandContent = CKEDITOR.instances['contentEditor'].getData();
            CommonService.showToast('success', 'Band has been saved');
            $window.location.href = $scope.returnLink;
        }, function (err) {
            $rootScope.error = err;
        })
    };

    $scope.canlcelEditing = function () {
        $window.location.href = $scope.returnLink;
    };

    $scope.uploadImage = function uploadImage(file, type) {
        if (file != null) {
            BandService.uploadImage(file, $scope.band.id, function (res) {
                CommonService.showToast('success', 'File uploaded!');
            }, function (err) {
                $rootScope.error = err;
            });
        }
    };

}]);