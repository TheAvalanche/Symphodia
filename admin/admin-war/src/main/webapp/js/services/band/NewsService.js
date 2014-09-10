(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('NewsService', ['$http', '$routeParams', function ($http, $routeParams) {
            var restRoot = '/admin/rest/news';
            var restRootBand = restRoot + '/' + $routeParams.bandId;

            return {
                all: function() {
                    return $http.get(restRootBand + '/all');
                },
                count: function() {
                    return $http.get(restRootBand + '/count');
                },
                part: function (offset, max) {
                    return $http.get(restRootBand + '/part/' + offset + '/' + max)
                },
                save: function(news) {
                    return $http.post(restRootBand + '/save', news);
                },
                remove: function(news) {
                    return $http.post(restRootBand + '/remove', news);
                }
            };
        }]);
}());