(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('MainCtrl', ['$scope', '$rootScope', 'BandService', 'MessageService', function ($scope, $rootScope, BandService, MessageService) {

            var init = function () {
                $scope.band = $rootScope.band || {};
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