(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListNewsCtrl', ['$scope', '$modal', 'NewsService', function ($scope, $modal, NewsService) {

            NewsService.getAll().
                success(function (data, status, headers, config) {
                    $scope.newsList = data;
                }).
                error(function (data, status, headers, config) {
                    $scope.newsList = [];
                });

            $scope.openAddNews = function() {
                var modalInstance = $modal.open({
                    templateUrl: '/admin/ng/partials/band/edit-news.html',
                    controller: 'EditNewsCtrl',
                    windowClass: 'admin-modal'
                });

                modalInstance.result.then(function (offeringBundleId) {
                    //save
                }, function () {
                    //cancel
                });
            }

        }]);
}());
