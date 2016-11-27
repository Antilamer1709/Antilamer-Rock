angular.module('mvcApp').controller('BandHistoryCtrl', ['$scope', '$rootScope', '$routeParams', 'BandService', '$window', 'CommonService', function ($scope, $rootScope, $routeParams, BandService, $window, CommonService) {

    $scope.bandId = $routeParams.bandId;
    $rootScope.home = false;
    $rootScope.bands = true;
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
            }
            // {
            //     name: 'action',
            //     displayName: 'commons.action',
            //     cellTemplate: '' +
            //     '<a href="#/configuration/{{row.entity.id}}' + liferayToken + '" ng-if="row.entity.attachmentDownloadUrl != undefined && row.entity.attachmentDownloadUrl != null" class="btn btn-primary btn-xs" target="_blank"> ' +
            //     '<span class="glyphicon glyphicon-download-alt"></span>&nbsp;{{"commons.download" | translate}}</a><a class="btn btn-primary btn-xs" style="margin-left: 4px;" href="#/configuration/{{row.entity.id}}">' +
            //     '<span class="glyphicon glyphicon-pencil"></span>&nbsp;{{"commons.edit" | translate}}</a>',
            //     width: 140,
            //     enableSorting: false,
            //     enableFiltering: false,
            //     headerCellFilter: 'translate',
            //     enableColumnMenu: false
            // }
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