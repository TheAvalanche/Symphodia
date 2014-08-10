(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', 'NewsService', 'FileUploader', 'news', function ($scope, $modalInstance, NewsService, FileUploader, news) {

            var init = function() {
                $scope.news = news;
            };

            $scope.save = function() {
                NewsService.save($scope.news).
                    success(function (data, status, headers, config) {
                        $modalInstance.close();
                    }).
                    error(function (data, status, headers, config) {
                        $modalInstance.dismiss('error');
                    });

            };

            $scope.cancel = function() {
                $modalInstance.dismiss('cancel');
            };

            $scope.uploader = new FileUploader();
            $scope.uploader.filters.push({
                name: 'imageFilter',
                fn: function(item /*{File|FileLikeObject}*/, options) {
                    var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                    return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
                }
            });

            init();

        }]);
}());
