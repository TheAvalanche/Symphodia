(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('ListNewsCtrl', ['$scope', '$modal', function ($scope, $modal) {
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
