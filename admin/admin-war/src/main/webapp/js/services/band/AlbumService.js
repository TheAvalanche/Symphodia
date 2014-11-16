(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('AlbumService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/album';
            var restRootBand = restRoot + '/' + ContextService.getBand().id;

            return {
                all: function() {
                    return $http.get(restRootBand + '/all');
                },
                save: function(album) {
                    return $http.post(restRootBand + '/save', album);
                },
                remove: function(album) {
                    return $http.post(restRootBand + '/remove', album);
                },
                albumTypes: function () {
                    return $http.get(restRoot + '/albumTypes');
                }
            };
        }]);
}());