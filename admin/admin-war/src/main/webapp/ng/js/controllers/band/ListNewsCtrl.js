(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListNewsCtrl', ['$scope', '$modal', 'NewsService', 'FileService', 'MessageService', function ($scope, $modal, NewsService, FileService, MessageService) {

            var init = function () {
                NewsService.getAll().success(function (data) {
                    $scope.newsList = data;
                });
            };

            $scope.editNews = function (news) {
                var modalInstance = $modal.open({
                    templateUrl: '/admin/ng/partials/band/edit-news.html',
                    controller: 'EditNewsCtrl',
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
                }, function () {
                    init();
                });
            };

            $scope.removeNews = function (news) {
                news.imageList.forEach(function (image) {
                    FileService.removeImage(image);
                });
                NewsService.remove(news).success(function () {
                    MessageService.warn("Removed");
                    init();
                });
            };

            $scope.minimizeText = function (text) {
                if (text.length >= 50) {
                    return text.substring(0, 50) + "...";
                }
                return text;
            };

            init();

        }]);
}());
