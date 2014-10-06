(function () {
    'use strict';

    angular.module('adminApp.filters')
        .filter('MusicPath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
            return function (name) {

                return $rootScope.getProperty('HOSTNAME') + "uploaded/" + ContextService.getBand().id + "/" + name + ".mp3";
            };
        }]);
}());