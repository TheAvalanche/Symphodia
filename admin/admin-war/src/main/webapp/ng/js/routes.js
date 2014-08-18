(function(){
    "use strict";

    angular.module('adminApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/band/news', {templateUrl: 'partials/band/list-news.html', controller: 'ListNewsCtrl'});
        $routeProvider.when('/common/property', {templateUrl: 'partials/band/list-property.html', controller: 'ListPropertyCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}}); //Page Not Found
        $routeProvider.otherwise({redirectTo: '/404'});
    }]);
}());