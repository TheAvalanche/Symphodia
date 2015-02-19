angular.module('frontApp.filters')
    .filter('ImagePath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
        return function (name, postfix) {
            var base = /*$rootScope.getProperty('HOSTNAME') TODO: fetch properties*/"http://localhost:8080/" + "uploaded/" + ContextService.getBandId() + "/" + name;
            if (postfix) {
                return base + "_" + postfix + ".png";
            }
            return base + ".png";
        };
    }]).filter('MinimizeText', function () {
        return function (text, limit) {
            if (text.length >= limit) {
                return text.substring(0, limit) + "...";
            }
            return text;
        };
    });