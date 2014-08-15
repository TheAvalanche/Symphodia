(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', '$filter', 'NewsService', 'FileService', 'FileUploader', 'news', function ($scope, $modalInstance, $filter, NewsService, FileService, FileUploader, news) {

            var init = function () {
                $scope.news = news;
                $scope.news.imageList = news.imageList || [];
                $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
            };

            $scope.save = function () {
                NewsService.save($scope.news).
                    success(function () {
                        $modalInstance.close();
                    }).
                    error(function () {
                        $modalInstance.dismiss('error');
                    });
            };

            $scope.cancel = function () {
                $modalInstance.dismiss('cancel');
            };

            $scope.uploader = new FileUploader({
                url: '/admin/rest/file/saveImage'
            });

            $scope.uploader.filters.push({
                name: 'imageFilter',
                fn: function (item) {
                    var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                    return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
                }
            });

            $scope.uploader.onAfterAddingFile = function (item) {
                var uniqueFileName = new Date().getTime().toString();
                item.alias = uniqueFileName;
                item.file.name = uniqueFileName;
                $scope.uploader.uploadItem(item);
            };

            $scope.uploader.onCompleteItem = function (item) {
                $scope.news.imageList.push(item.file.name);
                $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
            };

            $scope.removeImage = function (image) {
                FileService.removeImage(image).
                    success(function () {
                        $scope.news.imageList.splice($scope.news.imageList.indexOf(image), 1);
                        $scope.imageGroupList = $filter('GroupItems')($scope.news.imageList, 4);
                    })
            };

            init();

        }]);
}());
