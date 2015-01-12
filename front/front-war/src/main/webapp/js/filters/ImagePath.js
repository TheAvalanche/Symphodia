(function () {
    'use strict';

    angular.module('frontApp.filters')
        .filter('ImagePath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
            return function (name, postfix) {
                var base = $rootScope.getProperty('HOSTNAME') + "uploaded/" + ContextService.getBandId() + "/" + name;
                console.log(postfix);
                if (postfix) {
                    return base + "_" + postfix + ".png";
                }
                return base + ".png";
            };
        }]);
}());