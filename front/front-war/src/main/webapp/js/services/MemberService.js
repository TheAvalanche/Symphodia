(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('MemberService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/front/rest/member';
            var restRootBand = restRoot + '/' + ContextService.getBandId();

            return {
                all: function() {
                    return $http.get(restRootBand + '/all');
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