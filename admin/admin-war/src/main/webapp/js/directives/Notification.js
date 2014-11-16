(function () {
    'use strict';

    angular.module('adminApp.directives')
        .directive('sNotification', ['MessageService', function (MessageService) {
            return {
                restrict: 'E',
                transclude: true,
                templateUrl : '/admin/partials/templates/notification.html',
                link: function (scope) {
                    scope.getMessages = function () {
                        return MessageService.msg();
                    };
                    scope.onClose = function (index) {
                        MessageService.removeMessage(index);
                    };
                    scope.clearMessages = function () {
                        MessageService.clear();
                    };
                }
            };
        }]);
}());