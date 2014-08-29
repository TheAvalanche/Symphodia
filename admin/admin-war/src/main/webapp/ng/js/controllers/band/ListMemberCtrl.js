(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListMemberCtrl', ['$scope', '$rootScope', '$modal', 'MemberService', 'FileService', 'MessageService', function ($scope, $rootScope, $modal, MemberService, FileService, MessageService) {

            var init = function () {
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

            $scope.editNews = function (news) {
                var modalInstance = $modal.open({
                    templateUrl: '/admin/ng/partials/band/edit-member.html',
                    controller: 'EditMemberCtrl',
                    windowClass: 'admin-modal',
                    backdrop: 'static',
                    resolve: {
                        news: function () {
                            return news;
                        }
                    }
                });

                modalInstance.result.then(function () {
                    MessageService.success("Saved");
                    init();
                });
            };

            $scope.removeMember = function (member) {
                FileService.removeImage(member.image);
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