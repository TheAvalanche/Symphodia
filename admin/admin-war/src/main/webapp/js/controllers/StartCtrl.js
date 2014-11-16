(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('StartCtrl', ['$scope', 'ContextService', 'ClientService', function ($scope, ContextService, ClientService) {

            var init = function () {
                ClientService.getClient().success(function (data) {
                    ContextService.setClient(data);
                    $scope.client = ContextService.getClient();
                });
            };

            $scope.setBand = function (band) {
                ContextService.setBand(band);
            };

            init();
        }]);
}());