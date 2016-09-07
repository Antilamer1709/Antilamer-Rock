angular.module('mvcApp', ['ngRoute', 'ngAnimate', 'route-segment', 'view-segment'])
    .config(['$routeSegmentProvider', '$routeProvider', function ($routeSegmentProvider, $routeProvider) {
        $routeSegmentProvider
            .when('/', 'index')
            .when('/index', 'index')
            .when('/configuration', 'configuration')
            .when('/test2', 'test2')

        $routeSegmentProvider.segment('index', {
            templateUrl: 'HTML/test.html',
            controller: 'IndexCtrl'
        });

        $routeSegmentProvider.segment('configuration', {
            templateUrl: 'HTML/test.html',
            controller: 'ConfigurationCtrl'
        });

        $routeSegmentProvider.segment('test2', {
            templateUrl: 'HTML/test2.html',
            controller: 'Test2Ctrl'
        });

        $routeProvider.otherwise({redirectTo: '/'});
    }]);
