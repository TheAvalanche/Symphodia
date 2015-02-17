(function () {
    'use strict';

    angular.module('frontApp.directives')
        .directive('sScrollListener', function ($window) {
            return function(scope, element, attrs) {
                angular.element($window).bind("scroll", function() {
                    scope.show = this.pageYOffset > attrs["sScrollListener"];
                    scope.$apply();
                });
            };
        });
}());