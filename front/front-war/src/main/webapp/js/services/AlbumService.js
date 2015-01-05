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
                allFull: function () {
                    return $http.get(restRootBand + '/FULL/all');
                },
                allSingles: function () {
                    return $http.get(restRootBand + '/SINGLE/all');
                }
            };
        }]);
}());