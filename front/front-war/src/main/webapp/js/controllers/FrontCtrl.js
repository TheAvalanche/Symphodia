(function () {
    'use strict';

    angular.module('frontApp.controllers')
        .controller('FrontCtrl', ['$scope', '$modal', 'ContextService', 'BandService', 'NewsService', 'AlbumService', 'MemberService',
            function ($scope, $modal, ContextService, BandService, NewsService, AlbumService, MemberService) {
                BandService.byId(ContextService.getBandId()).success(function (data) {
                    $scope.band = data;
                    ContextService.setBand($scope.band);
                });
                NewsService.all().success(function (data) {
                    $scope.newsList = data;
                    $scope.firstNews = data[0];
                    $scope.newsList.shift();
                });
                AlbumService.allFull().success(function (data) {
                    $scope.fullAlbumList = data;
                });
                AlbumService.allSingles().success(function (data) {
                    $scope.singleAlbumList = data;
                });
                MemberService.all().success(function (data) {
                    $scope.memberList = data;
                });

                $scope.openAlbum = function (album) {
                    ContextService.setAlbum(album);
                    $modal.open({
                        templateUrl: 'album.html',
                        controller: 'AlbumCtrl',
                        resolve: {
                            album: function () {
                                return album;
                            }
                        }
                    });

                }
            }]);
}());