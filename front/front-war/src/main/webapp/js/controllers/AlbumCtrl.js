(function () {
    'use strict';

    angular.module('frontApp.controllers')
        .controller('AlbumCtrl', ['$scope', '$modalInstance', 'ContextService', 'SongService', 'album',
            function ($scope, $modalInstance, ContextService, SongService, album) {
                $scope.album = album;
                SongService.all().success(function (data) {
                    $scope.songList = data;
                });

                $scope.cancel = function () {
                    $modalInstance.close();
                };
            }]);
}());