(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('BandService', ['$http', function ($http) {
            var restRoot = '/front/rest/band';

            return {
                byId: function(bandId) {
                    return $http.get(restRoot + '/id/' + bandId);
                }
            };
        }]);
}());