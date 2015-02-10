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
                $scope.band.page.logoList = $scope.band.page.logoList || [];
                $scope.logoList = $scope.band.page.logoList;
            };

            $scope.save = function () {
                $scope.$broadcast("beforeSave");
                BandService.save($scope.band);
                $scope.editable = false;
            };

            $scope.cancel = function () {
                $scope.$broadcast("beforeCancel");
                $scope.editable = false;
            };

            $scope.edit = function () {
                $scope.editable = true;
            };

            init();

        }]);
}());