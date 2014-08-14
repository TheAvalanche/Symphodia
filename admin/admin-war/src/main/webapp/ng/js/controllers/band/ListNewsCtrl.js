(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListNewsCtrl', ['$scope', '$modal', 'NewsService', function ($scope, $modal, NewsService) {

            NewsService.getAll().
                success(function (data) {
                    $scope.newsList = data;
                }).
                error(function () {
                    $scope.newsList = [];
                });

            $scope.editNews = function(news) {
                var modalInstance = $modal.open({
                    templateUrl: '/admin/ng/partials/band/edit-news.html',
                    controller: 'EditNewsCtrl',
                    windowClass: 'admin-modal',
                    resolve: {
                        news: function() {
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

                modalInstance.result.then(function (offeringBundleId) {
                    //save
                }, function () {
                    //cancel
                });
            };

            $scope.removeNews = function(news) {
                NewsService.remove(news).
                    success(function (data) {
                        //removed
                    }).
                    error(function () {
                        //error
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
