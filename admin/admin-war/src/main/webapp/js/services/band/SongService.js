(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('SongService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/song/';

            return {
                all: function() {
                    return $http.get(restRoot + ContextService.getAlbum().id + '/all');
                },
                save: function(song) {
                    return $http.post(restRoot + ContextService.getAlbum().id + '/save', song);
                },
                remove: function(song) {
                    return $http.post(restRoot + ContextService.getAlbum().id + '/remove', song);
                }
            };
        }]);
}());