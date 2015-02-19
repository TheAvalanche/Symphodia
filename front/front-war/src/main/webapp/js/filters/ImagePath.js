(function () {
    'use strict';

    angular.module('frontApp.filters')
        .filter('ImagePath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
            return function (name, postfix) {
                var base = /*$rootScope.getProperty('HOSTNAME') TODO: fetch properties*/"http://localhost:8080/" + "uploaded/" + ContextService.getBandId() + "/" + name;
                if (postfix) {
                    return base + "_" + postfix + ".png";
                }
                return base + ".png";
            };
        }]);
}());