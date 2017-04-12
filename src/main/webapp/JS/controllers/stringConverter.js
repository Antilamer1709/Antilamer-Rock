angular.module('mvcApp').controller('StringConverterCtrl', ['$scope', '$rootScope', '$routeParams', 'ROLES', 'RUS_ALPHABET', 'ENG_ALPHABET', function ($scope, $rootScope, $routeParams, ROLES, RUS_ALPHABET, ENG_ALPHABET) {

    $rootScope.home = false;
    $rootScope.bands = false;
    $rootScope.chat = false;
    $rootScope.chat = false;


    $scope.$watch('stringToConvert', function (stringToConvert) {
        if (stringToConvert != undefined) {
            $scope.convertedString = convertString(stringToConvert);
        }
    });

    function convertString(string) {
        var search;
        var replace;
        var russian = checkRussianLanguage(string);
        if (russian){
            search = ENG_ALPHABET;
            replace = RUS_ALPHABET;
        } else {
            search = RUS_ALPHABET;
            replace = ENG_ALPHABET;
        }

        for (var i = 0; i < replace.length; i++) {
            var reg = new RegExp(replace[i], 'mig');
            string = string.replace(reg, function (a) {
                return a == a.toLowerCase() ? search[i] : search[i].toUpperCase();
            })
        }
        return string;
    }

    function checkRussianLanguage(string) {
        var regexp = /^[а-яё]+/i;
        return string.match(regexp);
    }

}]);