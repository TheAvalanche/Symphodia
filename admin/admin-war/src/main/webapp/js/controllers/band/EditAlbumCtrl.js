(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditAlbumCtrl', ['$scope', '$modalInstance', 'AlbumService', 'ContextService', 'album',
            function ($scope, $modalInstance, AlbumService, ContextService, album) {

                var init = function () {
                    $scope.band = ContextService.getBand();
                    $scope.album = album || {};
                    $scope.album.imageList = $scope.album.imageList || [];
                    $scope.imageList = $scope.album.imageList;
                    $scope.editable = true;
                    AlbumService.albumTypes().success(function(data) {
                        $scope.albumTypes = data;
                    });
                };

                $scope.save = function () {
                    $scope.$broadcast("beforeSave");
                    AlbumService.save($scope.album).success(function () {
                        $modalInstance.close();
                    });
                };

                $scope.cancel = function () {
                    $scope.$broadcast("beforeCancel");
                    $modalInstance.dismiss();
                };

                init();

            }]);
}());