(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('AlbumsCtrl', ['$scope', '$modal', 'AlbumService', 'FileService', 'MessageService', 'ContextService',
            function ($scope, $modal, AlbumService, FileService, MessageService, ContextService) {

                var init = function () {
                    $scope.band = ContextService.getBand();
                    AlbumService.all().success(function (data) {
                        $scope.albumList = data;
                    });
                };

                $scope.editAlbum = function (album) {
                    var modalInstance = $modal.open({
                        templateUrl: '/admin/partials/band/edit-album.html',
                        controller: 'EditAlbumCtrl',
                        windowClass: 'admin-modal',
                        backdrop: 'static',
                        resolve: {
                            album: function () {
                                return album;
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

                $scope.removeAlbum = function (album) {
                    album.imageList.forEach(function (image) {
                        FileService.removeImage(image);
                    });
                    AlbumService.remove(album).success(function () {
                        MessageService.warn("Removed");
                        init();
                    });
                };

                $scope.setAlbum = function (album) {
                    ContextService.setAlbum(album);
                };

                init();

            }]);
}());