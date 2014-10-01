(function(){
    "use strict";

    angular.module('adminApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/start', {templateUrl: 'partials/start.html', controller: 'StartCtrl'});
        $routeProvider.when('/band/band', {templateUrl: 'partials/band/band.html', controller: 'BandCtrl'});
        $routeProvider.when('/band/news', {templateUrl: 'partials/band/news.html', controller: 'NewsCtrl'});
        $routeProvider.when('/band/members', {templateUrl: 'partials/band/members.html', controller: 'MembersCtrl'});
        $routeProvider.when('/band/albums', {templateUrl: 'partials/band/albums.html', controller: 'AlbumsCtrl'});
        $routeProvider.when('/band/songs', {templateUrl: 'partials/band/songs.html', controller: 'SongsCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}}); //Page Not Found
        $routeProvider.otherwise({redirectTo: '/start'});
    }]);
}());