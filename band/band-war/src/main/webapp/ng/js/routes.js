(function(){
    "use strict";

    angular.module('bandApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/news', {templateUrl: 'partials/news.html', controller: 'NewsCtrl'});
        $routeProvider.when('/member', {templateUrl: 'partials/member.html', controller: 'MemberCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}}); //Page Not Found
        $routeProvider.otherwise({redirectTo: '/404'});
    }]);
}());