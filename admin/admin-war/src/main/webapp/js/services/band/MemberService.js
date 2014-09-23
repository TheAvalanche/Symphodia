(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('MemberService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/member';
            var restRootBand = restRoot + '/' + ContextService.getBand().id

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
                },
                instruments: function () {
                    return $http.get(restRoot + '/instruments');
                }
            };
        }]);
}());