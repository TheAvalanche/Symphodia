(function () {
    'use strict';

    angular.module('bandApp.services')
        .factory('PropertyService', ['$http', function ($http) {
            var restRoot = '/band/rest/property';

            return {
                getAll: function() {
                    return $http.get(restRoot + '/all');
                },
                save: function(property) {
                    return $http.post(restRoot + '/save', property);
                }
            };
        }]);
}());