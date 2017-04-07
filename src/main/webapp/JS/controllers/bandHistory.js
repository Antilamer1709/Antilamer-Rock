angular.module('mvcApp').controller('BandHistoryCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', '$window', 'CommonService', function ($scope, $rootScope, $routeParams, BandService, $window, CommonService) {

    $scope.bandId = $routeParams.bandId;
    $rootScope.home = false;
    $rootScope.bands = true;
    $rootScope.stringConverter = false;
    $rootScope.chat = false;
    $scope.returnLink = "#/band/" + $scope.bandId;
    $scope.band = {}

    $scope.gridOptions = {
        paginationPageSizes: [10, 25, 50],
        paginationPageSize: 10,
        columnDefs: [
            {
                name: 'id',
                displayName: 'Id',
                width: '*'
            },
            {
                name: 'user',
                displayName: 'User',
                width: '*'
            },
            {
                name: 'creationDate',
                displayName: 'Date',
                width: '*'
            },
            {
                name: 'action',
                displayName: 'Action',
                cellTemplate: '' +
                '<a href="#/band/history/details/{{grid.appScope.bandId}}/{{row.entity.id}}' +
                '"class="btn btn-primary" style="padding: 1px 5px;font-size: 12px; + ' +
                'line-height: 1.5;border-radius: 3px; color: #F4F2F2; margin: 5px; margin-left: 30px;" > View</a>',
                width: 100
                // enableSorting: false,
                // enableFiltering: false,
                // headerCellFilter: 'translate',
                // enableColumnMenu: false
            }
        ]
    };

    $scope.loadBand = function () {
        var params = {};
        params.id = $routeParams.bandId;
        BandService.getBand(params, function (res) {
                if (res != undefined) {
                    $scope.band = res;
                }
            }, function (err) {
                $rootScope.error = err;
            }
        );
    };
    $scope.loadBand();

    $scope.seachBandHistory = function () {
        var searchParams = {};
        searchParams.id = $scope.bandId;
        BandService.seachBandHistory(searchParams, function (res) {
                if (res != undefined) {
                    $scope.gridOptions.data = res;
                }
            }, function (err) {
                $rootScope.error = err;
            }
        );
    };
    $scope.seachBandHistory();




}]);