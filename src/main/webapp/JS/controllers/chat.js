angular.module('mvcApp').controller('ChatCtrl', ['$scope', '$rootScope', 'ChatService', function ($scope, $rootScope, ChatService) {

    $rootScope.home = false;
    $rootScope.bands = false;
    $rootScope.chat = true;

    $scope.messages = [];
    $scope.message = "";
    $scope.max = 70;

    $scope.addMessage = function() {
        ChatService.send($scope.message);
        $scope.message = "";
    };

    ChatService.receive().then(null, null, function(message) {
        $scope.messages.push(message);
    });

}]);