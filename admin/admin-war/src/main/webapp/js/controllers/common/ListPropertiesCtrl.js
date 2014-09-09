(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListPropertiesCtrl', ['$scope', '$filter', 'PropertyService', 'MessageService', function ($scope, $filter, PropertyService, MessageService) {

            $scope.save = function(property) {
                PropertyService.save(property)
                    .success(function () {
                        MessageService.success($filter('TitleText')(property.propertyKey) + " updated.");
                    });
            };
        }]);
}());