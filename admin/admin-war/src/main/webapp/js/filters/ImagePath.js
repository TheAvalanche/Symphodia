(function () {
    'use strict';

    angular.module('adminApp.filters')
        .filter('ImagePath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
            return function (name) {

                return $rootScope.getProperty('HOSTNAME') + "uploaded/" + ContextService.getBand().id + "/" + name + "_s.png";
            };
        }]);
}());