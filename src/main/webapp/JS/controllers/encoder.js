angular.module('mvcApp').controller('EncoderCtrl', ['$scope', '$rootScope', 'EncoderService', 'CommonService', function ($scope, $rootScope, EncoderService, CommonService) {

    $rootScope.home = false;
    $rootScope.bands = false;
    $rootScope.chat = false;
    $rootScope.chat = false;

    $scope.encoder = {};

    $scope.convert = function () {
        if ($scope.encoderForm.$valid){
            EncoderService.encode($scope.encoder, function (res) {
                $scope.encoder = res;
            }, function (err) {
                $rootScope.error = err;
            })
        } else {
            CommonService.showToast('danger', 'You must enter password and string which you want to encode!');
        }
    };

}]);