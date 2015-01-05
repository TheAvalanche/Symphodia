(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('NewsService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/news/';

            return {
                all: function() {
                    return $http.get(restRoot + ContextService.getBand().id + '/all');
                },
                count: function() {
                    return $http.get(restRoot + ContextService.getBand().id + '/count');
                },
                part: function (offset, max) {
                    return $http.get(restRoot + ContextService.getBand().id + '/part/' + offset + '/' + max)
                },
                save: function(news) {
                    return $http.post(restRoot + ContextService.getBand().id + '/save', news);
                },
                remove: function(news) {
                    return $http.post(restRoot + ContextService.getBand().id + '/remove', news);
                }
            };
        }]);
}());