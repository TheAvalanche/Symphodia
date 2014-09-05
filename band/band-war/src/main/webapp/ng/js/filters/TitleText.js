(function () {
    'use strict';

    angular.module('bandApp.filters')
        .filter('TitleText', function () {
            return function (text) {
                return text.split("_").join(" ").replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
            };
        });
}());