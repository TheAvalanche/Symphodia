(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListPropertyCtrl', ['$scope', 'PropertyService', function ($scope, PropertyService) {

            var init = function () {
                PropertyService.getAll().success(function (data) {
                    $scope.propertyList = data;
                });
            };

            init();
        }]);
}());