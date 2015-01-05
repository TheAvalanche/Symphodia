(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('AlbumService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/album';

            return {
                all: function() {
                    return $http.get(restRoot + ContextService.getBand().id + '/all');
                },
                save: function(album) {
                    return $http.post(restRoot + ContextService.getBand().id + '/save', album);
                },
                remove: function(album) {
                    return $http.post(restRoot + ContextService.getBand().id + '/remove', album);
                },
                albumTypes: function () {
                    return $http.get(restRoot + ContextService.getBand().id + '/albumTypes');
                }
            };
        }]);
}());