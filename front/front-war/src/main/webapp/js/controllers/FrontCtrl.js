(function () {
    'use strict';

    angular.module('frontApp.controllers')
        .controller('FrontCtrl', ['$scope', 'ContextService', 'BandService', 'NewsService',
            function ($scope, ContextService, BandService, NewsService) {
                BandService.byId(ContextService.getBandId()).success(function (data) {
                    $scope.band = data;
                    ContextService.setBand($scope.band);
                });
                NewsService.all().success(function (data) {
                    $scope.newsList = data;
                });
        }]);
}());