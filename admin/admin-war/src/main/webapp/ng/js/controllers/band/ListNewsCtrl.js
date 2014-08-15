(function () {
    'use strict';
    //todo image removal on delete;
    angular.module('adminApp.controllers')
        .controller('ListNewsCtrl', ['$scope', '$modal', 'NewsService', 'FileService', function ($scope, $modal, NewsService, FileService) {

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
                    resolve: {
                        news: function () {
                            if (news) {
                                return news;
                            } else {
                                return {
                                    creationDate: new Date()
                                }
                            }
                        }
                    }
                });

                modalInstance.result.then(function () {
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
                    init();
                });
            };

            $scope.minimizeText = function (text) {
                if (text.length >= 50) {
                    return text.substring(0, 50) + "...";
                }
                return text;
            };

        }]);
}());
