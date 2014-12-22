(function () {
    'use strict';

    angular.module('frontApp.filters')
        .filter('ImagePath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
            return function (name) {

                return $rootScope.getProperty('HOSTNAME') + "uploaded/" + ContextService.getBandId() + "/" + name + "_s.png";
            };
        }]);
}());