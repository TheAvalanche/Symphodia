(function(){
    "use strict";

    angular.module('adminApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/start', {templateUrl: 'partials/start.html', controller: 'StartCtrl'});
        $routeProvider.when('/band/:bandId/news', {templateUrl: 'partials/band/list-news.html', controller: 'ListNewsCtrl'});
        $routeProvider.when('/band/:bandId/members', {templateUrl: 'partials/band/list-members.html', controller: 'ListMembersCtrl'});
        $routeProvider.when('/common/properties', {templateUrl: 'partials/common/list-properties.html', controller: 'ListPropertiesCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}}); //Page Not Found
        $routeProvider.otherwise({redirectTo: '/start'});
    }]);
}());