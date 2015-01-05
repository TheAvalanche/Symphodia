(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('MemberService', ['$http', 'ContextService', function ($http, ContextService) {
            var restRoot = '/front/rest/member';
            var restRootBand = restRoot + '/' + ContextService.getBandId();

            return {
                all: function() {
                    return $http.get(restRootBand + '/all');
                }
            };
        }]);
}());