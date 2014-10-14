(function () {
    'use strict';

    angular.module('adminApp.filters')
        .filter('MinimizeText', function () {
            return function (text) {
                if (text.length >= 50) {
                    return text.substring(0, 50) + "...";
                }
                return text;
            };
        });
}());