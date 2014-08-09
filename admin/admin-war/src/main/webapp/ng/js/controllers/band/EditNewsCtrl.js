(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', 'NewsService', function ($scope, $modalInstance, NewsService) {

            var init = function() {
                $scope.news = {
                    creationDate: new Date()
                };
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
