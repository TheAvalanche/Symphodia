(function () {
    'use strict';

    angular.module('adminApp.controllers')
        .controller('EditSongCtrl', ['$scope', '$modalInstance', 'SongService', 'ContextService', 'song',
            function ($scope, $modalInstance, SongService, ContextService, song) {

                var init = function () {
                    $scope.band = ContextService.getBand();
                    $scope.album = ContextService.getAlbum();
                    $scope.song = song || {};
                    $scope.song.musicList = $scope.song.musicList || [];
                    $scope.musicList = $scope.song.musicList;
                    $scope.editable = true;
                    $scope.song.orderNumber = $scope.song.orderNumber || 1; //todo
                };

                $scope.save = function () {
                    $scope.$broadcast("beforeSave");
                    SongService.save($scope.song).success(function () {
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