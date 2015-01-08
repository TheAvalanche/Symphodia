"use strict";

var adminApp = angular.module('frontApp', [
    'frontApp.controllers',
    'frontApp.services',
    'frontApp.directives',
    'frontApp.filters',
    'ngSanitize',
    'ui.bootstrap',
    'gilbox.infiniteSlider',
    'duScroll']);

adminApp.config(function ($sceDelegateProvider) {
    $sceDelegateProvider.resourceUrlWhitelist([
        'self',
        'http://localhost:8080/**'
    ]);
});

adminApp.run(["$rootScope", "PropertyService", function($rootScope, PropertyService) {

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
