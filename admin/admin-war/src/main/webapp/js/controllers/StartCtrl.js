(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('StartCtrl', ['$scope', '$rootScope', 'PropertyService', 'MessageService', function ($scope, $rootScope, PropertyService, MessageService) {

            $scope.selectBand = function (band) {
                $rootScope.band = band;
            }

        }]);
}());