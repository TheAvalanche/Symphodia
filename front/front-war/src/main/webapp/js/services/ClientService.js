(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('ClientService', ['$http', function ($http) {
            var restRoot = '/front/rest/client';

            return {
                getClient: function() {
                    return $http.get(restRoot + '/client');
                }
            };
        }]);
}());