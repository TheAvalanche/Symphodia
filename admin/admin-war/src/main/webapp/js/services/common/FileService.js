(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('FileService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/file';
            var restRootBand = restRoot + '/' + ContextService.getBand().id;

            return {
                removeImage: function(filename) {
                    return $http.post(restRootBand + '/removeImage', filename);
                },
                removeLogo: function(filename) {
                    return $http.post(restRootBand + '/removeLogo', filename);
                },
                removeMusic: function(filename) {
                    return $http.post(restRootBand + '/removeMusic', filename);
                }
            };
        }]);
}());