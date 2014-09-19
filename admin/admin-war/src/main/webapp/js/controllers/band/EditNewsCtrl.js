(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$rootScope', '$modalInstance', '$filter', 'NewsService', 'FileService', 'FileUploader', 'news',
            function ($scope, $rootScope, $modalInstance, $filter, NewsService, FileService, FileUploader, news) {

            var init = function () {
                $scope.news = news || {};
                $scope.news.imageList = $scope.news.imageList || [];
                $scope.news.creationDate = $scope.news.creationDate || new Date();
                $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
                $scope.removeImageQueue = [];
                $scope.addImageQueue = [];
            };

            $scope.save = function () {
                $scope.removeImageQueue.forEach(function (image) {
                    FileService.removeImage(image);
                });
                NewsService.save($scope.news).success(function () {
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
                url: '/admin/rest/file/' +  $rootScope.band.id + '/saveImage',

                onAfterAddingFile: function (item) {
                    var uniqueFileName = new Date().getTime().toString();
                    item.alias = uniqueFileName;
                    item.file.name = uniqueFileName;
                    $scope.uploader.uploadItem(item);
                },

                onCompleteItem: function (item) {
                    $scope.addImageQueue.push(item.file.name);
                    $scope.news.imageList.push(item.file.name);
                    $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
                }
            });

            $scope.removeImage = function (image) {
                $scope.removeImageQueue.push(image);
                $scope.news.imageList.splice($scope.news.imageList.indexOf(image), 1);
                $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
            };

            init();

        }]);
}());
