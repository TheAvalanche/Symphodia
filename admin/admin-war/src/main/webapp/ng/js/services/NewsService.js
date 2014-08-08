(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('newsService', ['$http', function ($http) {
            var restRoot = '/admin/rest';

            return {
                getAll: function () {
                    return $http.get(restRoot + '/news/all');
                }
            };
        }]);
}());