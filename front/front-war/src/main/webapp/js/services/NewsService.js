(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('NewsService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/front/rest/news';
            var restRootBand = restRoot + '/' + ContextService.getBandId();

            return {
                all: function() {
                    return $http.get(restRootBand + '/all');
                },
                count: function() {
                    return $http.get(restRootBand + '/count');
                },
                part: function (offset, max) {
                    return $http.get(restRootBand + '/part/' + offset + '/' + max)
                }
            };
        }]);
}());