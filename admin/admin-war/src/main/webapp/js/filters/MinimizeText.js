(function () {
    'use strict';

    angular.module('adminApp.filters')
        .filter('MinimizeText', function () {
            return function (text) {
                if (text.length >= 100) {
                    return text.substring(0, 100) + "...";
                }
                return text;
            };
        });
}());