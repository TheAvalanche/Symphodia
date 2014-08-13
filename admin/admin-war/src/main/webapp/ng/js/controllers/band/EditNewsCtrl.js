(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', '$filter', 'NewsService', 'FileUploader', 'news', function ($scope, $modalInstance, $filter, NewsService, FileUploader, news) {

            var init = function () {
                $scope.news = news;
                $scope.news.imageList = news.imageList || [];
                $scope.imageGroupList = $filter('group')($scope.news.imageList, 4);
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
                url: '/admin/rest/file/save'
            });

            $scope.uploader.filters.push({
                name: 'imageFilter',
                fn: function (item) {
                    var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                    return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
                }
            });

            $scope.uploader.onAfterAddingFile = function (fileItem) {
                $scope.uploader.uploadItem(fileItem);
            };

            $scope.uploader.onCompleteItem = function (item) {
                $scope.news.imageList.push(item.file.name);
                $scope.imageGroupList = $filter('group')($scope.news.imageList, 4);
                $scope.$apply();
            };

            $scope.remove = function(image) {
                console.info(image);
            };

            init();

        }]).filter('group', function () {
            return function (items, groupItems) {
                if (items) {
                    var newArray = [];

                    for (var i = 0; i < items.length; i += groupItems) {
                        if (i + groupItems > items.length) {
                            newArray.push(items.slice(i));
                        } else {
                            newArray.push(items.slice(i, i + groupItems));
                        }
                    }

                    return newArray;
                }
            };
        });
}());
