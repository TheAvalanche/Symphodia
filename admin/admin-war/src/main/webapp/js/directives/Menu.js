(function () {
    'use strict';

    angular.module('adminApp.directives')
        .directive('sMenu', function () {
            return {
                restrict: 'E',
                transclude: true,
                templateUrl : '/admin/partials/templates/menu.html'
            };
        });
}());