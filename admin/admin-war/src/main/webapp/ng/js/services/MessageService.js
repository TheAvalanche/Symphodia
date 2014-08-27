(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('MessageService', ['$timeout', '$rootScope', function ($timeout, $rootScope) {
            var messages = [];

            var messageService = {
                success: function (msg) {
                    overrideAll();
                    pushWithTimeout(msg, 'alert-success');
                    closeAfterTimout(msg);
                },
                warn: function (msg) {
                    overrideAll();
                    pushWithTimeout(msg, 'alert-warning');
                    closeAfterTimout(msg);
                },
                error: function (msg) {
                    overrideAll();
                    pushWithTimeout(msg, 'alert-danger');
                    closeAfterTimout(msg);
                },
                msg: function () {
                    return messages;
                },
                clear: function () {
                    messages.length = 0;
                },
                removeMessage: function(index) {
                    messages.splice(index, 1);
                }
            };

            $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
                messageService.clear();
            });

            var closeAfterTimout = function(msg) {
                $timeout(function() {
                    if (_.contains(messages, msg)) {
                        messages.splice(messages.indexOf(msg), 1);
                    }
                }, 4000);
            };

            var pushWithTimeout = function(msg, cssClass) {
                $timeout(function() {
                    messages.push({txt: msg, cssClass: cssClass});
                }, 500);
            };

            var overrideAll = function() {
                messageService.clear();
            };

            return  messageService;
        }]);
}());