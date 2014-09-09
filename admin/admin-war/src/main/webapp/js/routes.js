(function(){
    "use strict";

    angular.module('adminApp').config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
        $routeProvider.when('/band/news', {templateUrl: 'partials/band/list-news.html', controller: 'ListNewsCtrl'});
        $routeProvider.when('/band/members', {templateUrl: 'partials/band/list-members.html', controller: 'ListMembersCtrl'});
        $routeProvider.when('/common/properties', {templateUrl: 'partials/common/list-properties.html', controller: 'ListPropertiesCtrl'});
        $routeProvider.when('/404', {controller: function() { window.location.href('/');}}); //Page Not Found
        $routeProvider.otherwise({redirectTo: '/404'});
    }]);
}());