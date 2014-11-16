"use strict";

var adminApp = angular.module('adminApp', [
    'adminApp.controllers',
    'adminApp.services',
    'adminApp.directives',
    'adminApp.filters',
    'ngRoute',
    'ngAnimate',
    'ngSanitize',
    'textAngular',
    'angularFileUpload',
    'ui.bootstrap']);

adminApp.config(function($sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([
        'self',
        'http://localhost:8080/**'
    ]);
});

adminApp.run(["$rootScope", "ClientService", "PropertyService", function($rootScope, ClientService, PropertyService) {

    PropertyService.getAll().success(function (data) {
        $rootScope.propertyList = data;
        $rootScope.getProperty = function (propertyKey) {
            for (var i = 0; i < $rootScope.propertyList.length; i++) {
                var property = $rootScope.propertyList[i];
                if (property.propertyKey === propertyKey) {
                    return property.value;
                }
            }
        }
    });

}]);
