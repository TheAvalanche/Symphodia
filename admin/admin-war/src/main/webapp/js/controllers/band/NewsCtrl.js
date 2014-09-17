(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('NewsCtrl', ['$scope', '$rootScope', '$modal', 'NewsService', 'FileService', 'MessageService', function ($scope, $rootScope, $modal, NewsService, FileService, MessageService) {

            var init = function () {
                $scope.currentPage = 1;
                NewsService.count().success(function (data) {
                    $scope.totalItems = data;
                });
                reloadNewsPart();
            };

            var reloadNewsPart = function () {
                NewsService.part(($scope.currentPage - 1) * $rootScope.getProperty('PAGE_SIZE'), $rootScope.getProperty('PAGE_SIZE')).success(function (data) {
                    $scope.newsList = data;
                });
            };

            $scope.editNews = function (news) {
                var modalInstance = $modal.open({
                    templateUrl: '/admin/partials/band/edit-news.html',
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

            $scope.pageChanged = function () {
                reloadNewsPart();
            };

            init();

        }]);
}());
