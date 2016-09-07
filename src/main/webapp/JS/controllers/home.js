angular.module('mvcApp').controller('HomeCtrl', ['$scope', '$rootScope', function ($scope, $rootScope) {

    $scope.hello = "Hello from ConfigurationCtrl";

    $scope.newUrl = "#/index";


}]);