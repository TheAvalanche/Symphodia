(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('MembersCtrl', ['$scope', '$rootScope', '$modal', 'MemberService', 'FileService', 'MessageService', 'ContextService',
            function ($scope, $rootScope, $modal, MemberService, FileService, MessageService, ContextService) {

            var init = function () {
                $scope.band = ContextService.getBand();
                $scope.currentPage = 1;
                MemberService.count().success(function (data) {
                    $scope.totalItems = data;
                });
                reloadMemberPart();
            };

            var reloadMemberPart = function () {
                MemberService.part(($scope.currentPage - 1) * $rootScope.getProperty('PAGE_SIZE'), $rootScope.getProperty('PAGE_SIZE')).success(function (data) {
                    $scope.memberList = data;
                });
            };

            $scope.editMember = function (member) {
                var modalInstance = $modal.open({
                    templateUrl: '/admin/partials/band/edit-member.html',
                    controller: 'EditMemberCtrl',
                    windowClass: 'admin-modal',
                    backdrop: 'static',
                    resolve: {
                        member: function () {
                            return member;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    MessageService.success("Saved");
                    init();
                }, function () {
                    init();
                });
            };

            $scope.removeMember = function (member) {
                member.imageList.forEach(function (image) {
                    FileService.removeImage(image);
                });
                MemberService.remove(member).success(function () {
                    MessageService.warn("Removed");
                    init();
                });
            };

            $scope.pageChanged = function () {
                reloadMemberPart();
            };

            init();

        }]);
}());