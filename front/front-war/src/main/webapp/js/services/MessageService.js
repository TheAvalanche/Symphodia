(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('MessageService', ['$timeout', '$rootScope', function ($timeout, $rootScope) {
            var messages = [];

            var messageService = {
                success: function (msg) {
                    addMsgWithTimeout({txt: msg, cssClass: 'alert-success'});
                },
                warn: function (msg) {
                    addMsgWithTimeout({txt: msg, cssClass: 'alert-warning'});
                },
                error: function (msg) {
                    addMsgWithTimeout({txt: msg, cssClass: 'alert-danger'});
                },
                msg: function () {
                    return messages;
                },
                clear: function () {
                    messages.length = 0;
                },
                removeMessage: function (index) {
                    messages.splice(index, 1);
                }
            };

            $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
                messageService.clear();
            });

            var addMsgWithTimeout = function (message) {
                messages.push(message);
                closeWithTimeout(message);
            };

            var closeWithTimeout = function(message) {
                $timeout(function() {
                    if (_.contains(messages, message)) {
                        messages.splice(messages.indexOf(message), 1);
                    }
                }, 3000);
            };

            return  messageService;
        }]);
}());