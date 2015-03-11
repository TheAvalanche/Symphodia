angular.module('frontApp.filters')
    .filter('ImagePath', ['$rootScope', 'ContextService', function ($rootScope, ContextService) {
        return function (name, postfix) {
            var base = $rootScope.getProperty('HOSTNAME') + "uploaded/" + ContextService.getBandId() + "/" + name;
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
    }).filter('TitleText', function () {
        return function (text) {
            return text.split("_").join(" ").replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
        };
    });