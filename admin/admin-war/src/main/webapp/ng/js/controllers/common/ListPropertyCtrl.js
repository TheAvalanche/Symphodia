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

            $scope.toTitle = function(text) {
                return text.split("_").join(" ").replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
            };

        }]);
}());