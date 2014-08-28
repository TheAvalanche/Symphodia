(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('MessageService', ['$timeout', '$rootScope', function ($timeout, $rootScope) {
            var messages = [];

            var messageService = {
                success: function (msg) {
                    addSingleMsgWithTimout({txt: msg, cssClass: 'alert-success'});
                },
                warn: function (msg) {
                    addSingleMsgWithTimout({txt: msg, cssClass: 'alert-warning'});
                },
                error: function (msg) {
                    addSingleMsgWithTimout({txt: msg, cssClass: 'alert-danger'});
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

            var addSingleMsgWithTimout = function (message) {
                addOrReplace(message);
                closeWithTimeout(message);
            };

            var closeWithTimeout = function(message) {
                $timeout(function() {
                    if (_.contains(messages, message)) {
                        messages.splice(messages.indexOf(message), 1);
                    }
                }, 3000);
            };

            var addOrReplace = function (message) {
                if (_.isEmpty(messages)) {
                    messages.push(message);
                } else {
                    messageService.clear();
                    $timeout(function () {
                        messages.push(message);
                    }, 500);
                }

            };

            return  messageService;
        }]);
}());