(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', 'NewsService', 'FileUploader', 'news', function ($scope, $modalInstance, NewsService, FileUploader, news) {

            var init = function() {
                $scope.news = news;
                $scope.news.imageList = [];
            };

            $scope.save = function() {
                $scope.uploader.uploadAll();
                NewsService.save($scope.news).
                    success(function () {
                        $modalInstance.close();
                    }).
                    error(function () {
                        $modalInstance.dismiss('error');
                    });

            };

            $scope.cancel = function() {
                $modalInstance.dismiss('cancel');
            };

            $scope.uploader = new FileUploader({
                url: '/admin/rest/file/save'
            });

            $scope.uploader.filters.push({
                name: 'imageFilter',
                fn: function(item) {
                    var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                    return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
                }
            });

            $scope.uploader.onAfterAddingFile = function(fileItem) {
                $scope.uploader.uploadItem(fileItem);
            };

            $scope.uploader.onCompleteItem = function(item, response, status, headers) {
                $scope.news.imageList.push(item.name);
                console.info($scope.news.imageList.length)
            };

            init();

        }]);
}());
