(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditNewsCtrl', ['$scope', '$modalInstance', 'NewsService', 'ContextService', 'news',
            function ($scope, $modalInstance, NewsService, ContextService, news) {

            var init = function () {
                $scope.band = ContextService.getBand();
                $scope.news = news || {};
                $scope.news.imageList = $scope.news.imageList || [];
                $scope.imageList = $scope.news.imageList;
                $scope.news.creationDate = $scope.news.creationDate || new Date();
            };

            $scope.save = function () {
                $scope.$emit("beforeSave");
                NewsService.save($scope.news).success(function () {
                    $modalInstance.close();
                });
            };

            $scope.cancel = function () {
                $scope.$emit("beforeCancel");
                $modalInstance.dismiss();
            };

            init();
        }]);
}());
