angular.module('mvcApp', ['ngRoute', 'ngAnimate', 'route-segment', 'view-segment'])
    .config(['$routeSegmentProvider', '$routeProvider', function ($routeSegmentProvider, $routeProvider) {
        $routeSegmentProvider
            .when('/', 'index')
            .when('/index', 'index')
            .when('/configuration', 'configuration')
            .when('/band/:bandId', 'band')

        $routeSegmentProvider.segment('index', {
            templateUrl: 'HTML/home.html',
            controller: 'IndexCtrl'
        });

        $routeSegmentProvider.segment('band', {
            templateUrl: 'HTML/band.html',
            controller: 'BandCtrl',
            dependencies: ['bandId']
        });

        $routeProvider.otherwise({redirectTo: '/'});
    }]);
