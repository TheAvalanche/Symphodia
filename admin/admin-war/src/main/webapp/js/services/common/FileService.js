(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('FileService', ['$http', '$rootScope', function ($http, $rootScope) {
            var restRoot = '/admin/rest/file';
            var restRootBand = restRoot + '/' + $rootScope.band.id;

            return {
                removeImage: function(filename) {
                    return $http.post(restRootBand + '/removeImage', filename);
                }
            };
        }]);
}());