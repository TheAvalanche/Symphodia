(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', '$filter', 'NewsService', 'FileService', 'FileUploader', 'news', function ($scope, $modalInstance, $filter, NewsService, FileService, FileUploader, news) {

            var init = function () {
                $scope.news = news;
                $scope.news.imageList = news.imageList || [];
                $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
                $scope.removeQueue = [];
            };

            $scope.save = function () {
                $scope.removeQueue.forEach(function (image) {
                    FileService.removeImage(image);
                });
                NewsService.save($scope.news).success(function () {
                    $modalInstance.dismiss();
                });
            };

            $scope.cancel = function () {
                $modalInstance.cancel();
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
                    $scope.news.imageList.push(item.file.name);
                    $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
                }
            });

            $scope.removeImage = function (image) {
                $scope.removeQueue.push(image);
                $scope.news.imageList.splice($scope.news.imageList.indexOf(image), 1);
                $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
            };

            init();

        }]);
}());
