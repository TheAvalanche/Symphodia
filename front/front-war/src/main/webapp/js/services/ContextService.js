(function () {
    'use strict';

    angular.module('frontApp.services')
        .factory('ContextService', function () {
            var band;
            var album;
            var bandId = 1000;
            var properties;

            var contextService = {
                setBand: function (arg) {
                    band = arg;
                },
                getBand: function () {
                    return band;
                },
                getBandId: function () {
                    return bandId;
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