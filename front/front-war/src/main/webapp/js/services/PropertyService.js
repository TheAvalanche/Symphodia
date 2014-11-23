(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('PropertyService', ['$http', function ($http) {
            var restRoot = '/front/rest/property';

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