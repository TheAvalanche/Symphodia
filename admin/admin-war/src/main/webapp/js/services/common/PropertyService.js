(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('PropertyService', ['$http', function ($http) {
            var restRoot = '/admin/rest/property';

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