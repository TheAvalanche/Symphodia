(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('SongService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/front/rest/song/';

            return {
                all: function() {
                    return $http.get(restRoot + ContextService.getAlbum().id + '/all');
                }
            };
        }]);
}());