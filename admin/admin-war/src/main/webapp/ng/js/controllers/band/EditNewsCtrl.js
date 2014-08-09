(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', 'NewsService', 'news', function ($scope, $modalInstance, NewsService, news) {

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


            init();

        }]);
}());
