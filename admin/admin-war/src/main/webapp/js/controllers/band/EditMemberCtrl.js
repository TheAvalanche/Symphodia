(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditMemberCtrl', ['$scope', '$modalInstance', 'MemberService', 'ContextService', 'member',
            function ($scope, $modalInstance, MemberService, ContextService, member) {

            var init = function () {
                $scope.band = ContextService.getBand();
                $scope.member = member || {};
                $scope.member.imageList = $scope.member.imageList || [];
                $scope.imageList = $scope.member.imageList;
                MemberService.instruments().success(function(data) {
                    $scope.instruments = data;
                });
            };

            $scope.save = function () {
                $scope.$emit("beforeSave");
                MemberService.save($scope.member).success(function () {
                    $modalInstance.close();
                });
            };

            $scope.cancel = function () {
                $scope.$emit("beforeCancel");
                $modalInstance.dismiss();
            };

            init();

        }]);
}());