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

            $scope.openEditNews = function(news) {
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
            }

        }]);
}());
