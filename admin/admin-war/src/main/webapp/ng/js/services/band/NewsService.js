(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('NewsService', ['$http', function ($http) {
            var restRoot = '/admin/rest/news';

            return {
                getAll: function() {
                    return $http.get(restRoot + '/all');
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