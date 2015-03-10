angular.module('frontApp.controllers')
    .controller('FrontCtrl', ['$scope', '$modal', '$timeout', 'ContextService', 'BandService', 'NewsService', 'AlbumService', 'MemberService',
        function ($scope, $modal, $timeout, ContextService, BandService, NewsService, AlbumService, MemberService) {
            BandService.byId(ContextService.getBandId()).success(function (data) {
                $scope.band = data;
                ContextService.setBand($scope.band);
            });
            NewsService.all().success(function (data) {
                $scope.newsList = data;
            });
            NewsService.allHot().success(function (data) {
                $scope.hotNewsList = data;
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
                    size: 'lg',
                    resolve: {
                        album: function () {
                            return album;
                        }
                    }
                });
            };

            $scope.openMember = function (member) {
                $modal.open({
                    templateUrl: 'member.html',
                    controller: 'MemberCtrl',
                    size: 'lg',
                    resolve: {
                        member: function () {
                            return member;
                        }
                    }
                });
            };

            $scope.openNews = function (news) {
                $modal.open({
                    templateUrl: 'news.html',
                    controller: 'NewsCtrl',
                    size: 'lg',
                    resolve: {
                        news: function () {
                            return news;
                        }
                    }
                });
            };
        }]
    ).controller('AlbumCtrl', ['$scope', '$modalInstance', 'ContextService', 'SongService', 'album',
        function ($scope, $modalInstance, ContextService, SongService, album) {
            $scope.album = album;
            SongService.all().success(function (data) {
                $scope.songList = data;
            });

            $scope.cancel = function () {
                $modalInstance.close();
            };
        }]
    ).controller('MemberCtrl', ['$scope', '$modalInstance', 'ContextService', 'member',
        function ($scope, $modalInstance, ContextService, member) {
            $scope.member = member;

            $scope.cancel = function () {
                $modalInstance.close();
            };
        }]
    ).controller('NewsCtrl', ['$scope', '$modalInstance', 'ContextService', 'news',
        function ($scope, $modalInstance, ContextService, news) {
            $scope.news = news;

            $scope.cancel = function () {
                $modalInstance.close();
            };
        }]
);