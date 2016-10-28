angular.module('mvcApp')
    .constant('ROLES', {
        ADMIN: "ROLE_ADMIN",
        USER: "ROLE_USER",
        SUPER_ADMIN: "ROLE_SUPER_ADMIN",
    })
    .config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push(['$q', '$location', '$injector', '$rootScope', function ($q, $location, $injector, $rootScope) {
            return {
                'responseError': function (response) {
                    var error = response.data;
                    var cmnSrv = $injector.get('CommonService');
                    if (response.status == 500) {
                        cmnSrv.showToast('danger', response.data.message)
                    }
                    if (response.status == 403) {
                        cmnSrv.showToast('danger', 'Unauthorized access')
                    }
                    return $q.reject(response);
                }
            }
        }]);
    }])
;