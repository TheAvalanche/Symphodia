(function () {
    'use strict';

    angular.module('frontApp.controllers')
        .controller('FrontCtrl', ['$scope', 'ContextService', 'BandService', function ($scope, ContextService, BandService) {
            BandService.byId(1000).success(function (data) {
                $scope.band = data;
            })
        }]);
}());