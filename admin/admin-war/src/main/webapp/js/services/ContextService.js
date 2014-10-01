(function () {
    'use strict';

    angular.module('adminApp.services')
        .factory('ContextService', function () {
            var band;
            var client;
            var album;
            var properties;

            var contextService = {
                setBand: function (arg) {
                    band = arg;
                },
                getBand: function () {
                    return band;
                },
                setClient: function (arg) {
                    client = arg;
                },
                getClient: function () {
                    return client;
                },
                setAlbum: function (arg) {
                    album = arg;
                },
                getAlbum: function () {
                    return album;
                }
            };

            return contextService;
        });
}());