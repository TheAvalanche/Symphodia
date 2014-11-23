(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('SongService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/front/rest/song';
            var restRootAlbum = restRoot + '/' + ContextService.getAlbum().id;

            return {
                all: function() {
                    return $http.get(restRootAlbum + '/all');
                },
                save: function(song) {
                    return $http.post(restRootAlbum + '/save', song);
                },
                remove: function(song) {
                    return $http.post(restRootAlbum + '/remove', song);
                }
            };
        }]);
}());