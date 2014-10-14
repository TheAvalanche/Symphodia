(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('ClientService', ['$http', function ($http) {
            var restRoot = '/admin/rest/client';

            return {
                getClient: function() {
                    return $http.get(restRoot + '/client');
                }
            };
        }]);
}());