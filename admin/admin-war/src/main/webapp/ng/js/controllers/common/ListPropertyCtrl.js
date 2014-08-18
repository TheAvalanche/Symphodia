(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListPropertyCtrl', ['$scope', 'PropertyService', function ($scope, PropertyService) {

            var init = function() {
                PropertyService.getAll().success(function (data) {
                    $scope.propertyList = data;
                });
            };

            $scope.save = function(property) {
                PropertyService.save(property).success(function () {
                    init();
                });
            };

            init();
        }]);
}());