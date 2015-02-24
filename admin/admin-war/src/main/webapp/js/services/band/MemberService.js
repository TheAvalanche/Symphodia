(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('MemberService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/admin/rest/member/';
            return {
                all: function() {
                    return $http.get(restRoot + ContextService.getBand().id + '/all');
                },
                save: function(news) {
                    return $http.post(restRoot + ContextService.getBand().id + '/save', news);
                },
                remove: function(news) {
                    return $http.post(restRoot + ContextService.getBand().id + '/remove', news);
                },
                instruments: function () {
                    return $http.get(restRoot + 'instruments');
                }
            };
        }]);
}());