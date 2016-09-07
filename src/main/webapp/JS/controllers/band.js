angular.module('mvcApp').controller('BandCtrl', ['$scope', '$rootScope', '$routeParams', function ($scope, $rootScope, $routeParams) {

    $scope.bandId = $routeParams.bandId;


}]);