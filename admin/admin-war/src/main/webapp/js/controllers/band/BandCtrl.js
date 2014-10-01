(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('BandCtrl', ['$scope', 'BandService', 'MessageService', 'ContextService',
            function ($scope, BandService, MessageService, ContextService) {

            var init = function () {
                $scope.band = ContextService.getBand() || {};
                $scope.band.page = $scope.band.page || {};
                $scope.band.page.imageList = $scope.band.page.imageList || [];
                $scope.imageList = $scope.band.page.imageList;
            };

            $scope.save = function () {
                $scope.$emit("beforeSave");
                BandService.save($scope.band);
            };

            $scope.cancel = function () {
                $scope.$emit("beforeCancel");
            };

            init();

        }]);
}());