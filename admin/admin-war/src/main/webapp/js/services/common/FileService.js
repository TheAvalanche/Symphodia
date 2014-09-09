(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('FileService', ['$http', function ($http) {
            var restRoot = '/admin/rest/file';

            return {
                removeImage: function(filename) {
                    return $http.post(restRoot + '/removeImage', filename);
                }
            };
        }]);
}());