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
    }).directive('sScrollListener', function ($window) {
        return function(scope, element, attrs) {
            angular.element($window).bind("scroll", function() {
                scope.show = this.pageYOffset > attrs["sScrollListener"];
                scope.$apply();
            });
        };
    }).directive('sReel', ['$timeout', function ($timeout) {
        return {
            link: function (scope, element, attrs) {
                var pos = 0;

                scope.moveForward = function () {
                    if (pos <= -document.getElementById("news-article-container").scrollWidth + window.innerWidth - 65) {
                        return;
                    }
                    scope.movePromise = $timeout(function() {
                        pos -= 20;
                        document.getElementById("news-article-container").style.left = pos + "px";
                        scope.moveForward();
                    }, 10);
                };

                scope.moveBackward = function () {
                    if (pos >= 0) {
                        return;
                    }
                    scope.movePromise = $timeout(function() {
                        pos += 20;
                        document.getElementById("news-article-container").style.left = pos + "px";
                        if (pos != 0) {
                            scope.moveBackward();
                        }
                    }, 10);
                };

                scope.moveStop = function () {
                    $timeout.cancel(scope.movePromise)
                }
            }
        }
    }]);