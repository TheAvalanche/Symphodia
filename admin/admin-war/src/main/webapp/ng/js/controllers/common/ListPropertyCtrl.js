(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListPropertyCtrl', ['$scope', 'PropertyService', 'MessageService', function ($scope, PropertyService, MessageService) {

            $scope.save = function(property) {
                PropertyService.save(property)
                    .success(function () {
                        MessageService.success($scope.toTitle(property.propertyKey) + " updated.");
                    });
            };
        }]);
}());