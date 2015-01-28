(function () {
    'use strict';

    angular.module('frontApp.filters')
        .filter('MinimizeText', function () {
            return function (text, limit) {
                if (text.length >= limit) {
                    return text.substring(0, limit) + "...";
                }
                return text;
            };
        });
}());