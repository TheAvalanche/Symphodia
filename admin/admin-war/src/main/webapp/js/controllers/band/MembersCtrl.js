(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('MembersCtrl', ['$scope', '$modal', '$filter', 'MemberService', 'FileService', 'MessageService', 'ContextService',
            function ($scope, $modal, $filter, MemberService, FileService, MessageService, ContextService) {

            var init = function () {
                $scope.band = ContextService.getBand();
                MemberService.all().success(function (data) {
                    $scope.memberList = data;
                    $scope.memberGroupList = $filter('GroupItems')($scope.memberList, 4);
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

            init();

        }]);
}());