(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListPropertyCtrl', ['$scope', 'PropertyService', function ($scope, PropertyService) {

            $scope.save = function(property) {
                PropertyService.save(property).success(function () {

                });
            };

            $scope.toTitle = function(text) {
                return text.split("_").join(" ").replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
            };

        }]);
}());