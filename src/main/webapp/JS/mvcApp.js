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
            .when('/chat', 'chat')
            .when('/stringConverter', 'stringConverter')
            .when('/band/:bandId', 'band')
            .when('/band/edit/:bandId', 'editBand')
            .when('/band/history/:bandId', 'bandHistory')
            .when('/band/history/details/:bandId/:versionId', 'bandHistoryDetails');

        $routeSegmentProvider.segment('index', {
            templateUrl: 'HTML/home.html',
            controller: 'IndexCtrl'
        });

        $routeSegmentProvider.segment('chat', {
            templateUrl: 'HTML/chat.html',
            controller: 'ChatCtrl'
        });

        $routeSegmentProvider.segment('stringConverter', {
            templateUrl: 'HTML/stringConverter.html',
            controller: 'StringConverterCtrl'
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

        $routeSegmentProvider.segment('bandHistoryDetails', {
            templateUrl: 'HTML/band.html',
            controller: 'BandHistoryDetails',
            dependencies: ['bandId', 'versionId']
        });

        $routeProvider.otherwise({redirectTo: '/'});
    }]);
