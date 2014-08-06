(function(){
    "use strict";

    angular.module('adminApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/band/news', {templateUrl: 'partials/band/list_news.html', controller: 'ListNewsCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}});//Page Not Found
        $routeProvider.otherwise({redirectTo: '/404'});
    }]);
}());