(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('MessageService', ['$timeout', '$rootScope', function ($timeout, $rootScope) {
            var messages = [];

            var messageService = {
                success: function (msg) {
                    messages.push({txt: msg, cssClass: 'alert-success'});
                },
                warn: function (msg) {
                    messages.push({txt: msg, cssClass: 'alert-warning'});
                },
                error: function (msg) {
                    messages.push({txt: msg, cssClass: 'alert-danger'});
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

            return  messageService;
        }]);
}());