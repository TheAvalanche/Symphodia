(function () {
    'use strict';

    angular.module('frontApp.controllers')
        .controller('MemberCtrl', ['$scope', '$modalInstance', 'ContextService', 'member',
            function ($scope, $modalInstance, ContextService, member) {
                $scope.member = member;

                $scope.cancel = function () {
                    $modalInstance.close();
                };
            }]);
}());