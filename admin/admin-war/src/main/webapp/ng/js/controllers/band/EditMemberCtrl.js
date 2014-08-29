(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditMemberCtrl', ['$scope', '$modalInstance', '$filter', 'MemberService', 'FileService', 'FileUploader', 'member', function ($scope, $modalInstance, $filter, MemberService, FileService, FileUploader, member) {

            var init = function () {
                $scope.member = member || {};
                $scope.member.image = $scope.member.image || [];
                $scope.removeImageQueue = [];
                $scope.addImageQueue = [];
            };

            $scope.save = function () {
                $scope.removeImageQueue.forEach(function (image) {
                    FileService.removeImage(image);
                });
                MemberService.save($scope.member).success(function () {
                    $modalInstance.close();
                });
            };

            $scope.cancel = function () {
                $scope.addImageQueue.forEach(function (image) {
                    FileService.removeImage(image);
                });
                $modalInstance.dismiss();
            };

            $scope.uploader = new FileUploader({
                url: '/admin/rest/file/saveImage',

                onAfterAddingFile: function (item) {
                    var uniqueFileName = new Date().getTime().toString();
                    item.alias = uniqueFileName;
                    item.file.name = uniqueFileName;
                    $scope.uploader.uploadItem(item);
                },

                onCompleteItem: function (item) {
                    $scope.addImageQueue.push(item.file.name);
                    $scope.member.image = item.file.name;
                }
            });

            $scope.removeImage = function (image) {
                $scope.removeImageQueue.push(image);
                $scope.member.image = null;
            };

            init();

        }]);
}());