angular.module('mvcApp')
    .constant('ROLES', {
        ADMIN: "ROLE_ADMIN",
        USER: "ROLE_USER",
        SUPER_ADMIN: "ROLE_SUPER_ADMIN",
    })
    .constant('RUS_ALPHABET', ["й","ц","у","к","е","н","г","ш","щ","з","х","ъ",
        "ф","ы","в","а","п","р","о","л","д","ж","э",
        "я","ч","с","м","и","т","ь","б","ю"])
    .constant('ENG_ALPHABET', ["q","w","e","r","t","y","u","i","o","p","\\[","\\]",
        "a","s","d","f","g","h","j","k","l",";","'",
        "z","x","c","v","b","n","m",",","\\."])
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push(['$q', '$location', '$injector', '$window', '$rootScope', function ($q, $location, $injector, $window, $rootScope) {
            return {
                'responseError': function (response) {
                    var error = response.data;
                    var cmnSrv = $injector.get('CommonService');
                    if (response.status == 500) {
                        cmnSrv.showToast('danger', response.data.message)
                    }
                    if (response.status == 403) {
                        $window.location.href = '/#/';
                    }
                    return $q.reject(response);
                }
            }
        }]);
    }])
;