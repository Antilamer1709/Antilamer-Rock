angular.module('mvcApp').factory('CommonService', ['$http', '$rootScope', 'ngToast', function ($http,  $rootScope, ngToast) {

    return {
        showToast: function (className, content) {
            ngToast.create({
                className: className,
                horizontalPosition: 'left',
                content: '<a href="#" class="">' + content + '</a>'
            });
        }
    }
}]);
