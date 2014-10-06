(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('SongsCtrl', ['$scope', '$modal', 'SongService', 'FileService', 'MessageService', 'ContextService',
            function ($scope, $modal, SongService, FileService, MessageService, ContextService) {

                var init = function () {
                    $scope.band = ContextService.getBand();
                    $scope.album = ContextService.getAlbum();
                    SongService.all().success(function (data) {
                        $scope.songList = data;
                    });
                };

                $scope.editSong = function (song) {
                    var modalInstance = $modal.open({
                        templateUrl: '/admin/partials/band/edit-song.html',
                        controller: 'EditSongCtrl',
                        windowClass: 'admin-modal',
                        backdrop: 'static',
                        resolve: {
                            song: function () {
                                return song;
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

                $scope.removeSong = function (song) {
                    SongService.remove(song).success(function () {
                        MessageService.warn("Removed");
                        init();
                    });
                };

                init();

            }]);
}());