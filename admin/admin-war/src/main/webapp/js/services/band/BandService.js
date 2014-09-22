(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('BandService', ['$http', '$rootScope', function ($http, $rootScope) {
            var restRoot = '/admin/rest/band';

            return {
                all: function() {
                    return $http.get(restRoot + '/all');
                },
                save: function(band) {
                    return $http.post(restRoot + '/save', band);
                },
                remove: function(band) {
                    return $http.post(restRoot + '/remove', band);
                }
            };
        }]);
}());