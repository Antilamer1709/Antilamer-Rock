angular.module('mvcApp', [
    'ngRoute',
    'ngAnimate',
    'route-segment',
    'view-segment',
    'ngToast',
    'ngFileUpload',
    'toggle-switch',
    'ui.grid',
    'ui.grid.pagination'])
    .config(['$routeSegmentProvider', '$routeProvider', function ($routeSegmentProvider, $routeProvider) {
        $routeSegmentProvider
            .when('/', 'index')
            .when('/index', 'index')
            .when('/configuration', 'configuration')
            .when('/band/:bandId', 'band')
            .when('/band/edit/:bandId', 'editBand')
            .when('/band/history/:bandId', 'bandHistory')

        $routeSegmentProvider.segment('index', {
            templateUrl: 'HTML/home.html',
            controller: 'IndexCtrl'
        });

        $routeSegmentProvider.segment('band', {
            templateUrl: 'HTML/band.html',
            controller: 'BandCtrl',
            dependencies: ['bandId']
        });

        $routeSegmentProvider.segment('editBand', {
            templateUrl: 'HTML/editBand.html',
            controller: 'EditBandCtrl',
            dependencies: ['bandId']
        });

        $routeSegmentProvider.segment('bandHistory', {
            templateUrl: 'HTML/bandHistory.html',
            controller: 'BandHistoryCtrl',
            dependencies: ['bandId']
        });

        $routeProvider.otherwise({redirectTo: '/'});
    }]);
