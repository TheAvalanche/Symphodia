(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('AlbumService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/front/rest/album';
            var restRootBand = restRoot + '/' + ContextService.getBandId();

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