(function () {
    'use strict';

    angular.module('frontApp.directives')
        .directive('sBackImg', function(){
            return function(scope, element, attrs){
                attrs.$observe('sBackImg', function(value) {
                    element.css({
                        'background-image': 'url(' + value +')',
                        'background-size' : 'cover'
                    });
                });
            };
        });
}());