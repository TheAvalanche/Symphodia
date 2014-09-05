"use strict";

var bandApp = angular.module('bandApp', [
    'bandApp.controllers',
    'bandApp.services',
    'bandApp.directives',
    'bandApp.filters',
    'ngRoute',
    'ngAnimate',
    'ngSanitize',
    'ui.bootstrap']);

bandApp.run(["$rootScope", "PropertyService", function($rootScope, PropertyService) {

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
