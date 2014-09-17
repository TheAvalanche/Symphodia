(function(){
    "use strict";

    angular.module('adminApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/start', {templateUrl: 'partials/start.html', controller: 'StartCtrl'});
        $routeProvider.when('/band/:bandId/main', {templateUrl: 'partials/band/main.html', controller: 'MainCtrl'});
        $routeProvider.when('/band/:bandId/news', {templateUrl: 'partials/band/news.html', controller: 'NewsCtrl'});
        $routeProvider.when('/band/:bandId/members', {templateUrl: 'partials/band/members.html', controller: 'MembersCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}}); //Page Not Found
        $routeProvider.otherwise({redirectTo: '/start'});
    }]);
}());