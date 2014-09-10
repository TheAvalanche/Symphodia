(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('NewsService', ['$http', '$routeParams', function ($http, $routeParams) {
            var restRoot = '/admin/rest/news' + $routeParams.bandId;

            return {
                all: function() {
                    return $http.get(restRoot + '/all');
                },
                count: function() {
                    return $http.get(restRoot + '/count');
                },
                part: function (offset, max) {
                    return $http.get(restRoot + '/part/' + offset + '/' + max)
                },
                save: function(news) {
                    return $http.post(restRoot + '/save', news);
                },
                remove: function(news) {
                    return $http.post(restRoot + '/remove', news);
                }
            };
        }]);
}());