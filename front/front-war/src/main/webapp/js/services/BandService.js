(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('BandService', ['$http', function ($http) {
            var restRoot = '/front/rest/band';

            return {
                all: function() {
                    return $http.get(restRoot + '/all');
                },
                save: function(band) {
                    return $http.post(restRoot + '/save', band);
                },
                remove: function(band) {
                    return $http.post(restRoot + '/remove', band);
                },
                byId: function(bandId) {
                    return $http.get(restRoot + '/id/' + bandId);
                }
            };
        }]);
}());