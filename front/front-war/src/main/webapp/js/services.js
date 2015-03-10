angular.module('frontApp.services')
    .factory('NewsService', ['$http', 'ContextService', function ($http, ContextService) {
        var restRoot = '/front/rest/news';
        var restRootBand = restRoot + '/' + ContextService.getBandId();

        return {
            all: function() {
                return $http.get(restRootBand + '/all');
            },
            allHot: function() {
                return $http.get(restRootBand + '/true/all');
            },
            count: function() {
                return $http.get(restRootBand + '/count');
            },
            part: function (offset, max) {
                return $http.get(restRootBand + '/part/' + offset + '/' + max)
            }
        };
    }]).factory('AlbumService', ['$http', 'ContextService', function ($http, ContextService) {
        var restRoot = '/front/rest/album';
        var restRootBand = restRoot + '/' + ContextService.getBandId();

        return {
            all: function() {
                return $http.get(restRootBand + '/all');
            },
            allFull: function () {
                return $http.get(restRootBand + '/FULL/all');
            },
            allSingles: function () {
                return $http.get(restRootBand + '/SINGLE/all');
            }
        };
    }]).factory('BandService', ['$http', function ($http) {
        var restRoot = '/front/rest/band';

        return {
            byId: function(bandId) {
                return $http.get(restRoot + '/id/' + bandId);
            }
        };
    }]).factory('MemberService', ['$http', 'ContextService', function ($http, ContextService) {
        var restRoot = '/front/rest/member';
        var restRootBand = restRoot + '/' + ContextService.getBandId();

        return {
            all: function() {
                return $http.get(restRootBand + '/all');
            }
        };
    }]).factory('SongService', ['$http', 'ContextService', function ($http, ContextService) {
        var restRoot = '/front/rest/song/';

        return {
            all: function() {
                return $http.get(restRoot + ContextService.getAlbum().id + '/all');
            }
        };
    }]).factory('PropertyService', ['$http', function ($http) {
        var restRoot = '/front/rest/property';

        return {
            getAll: function() {
                return $http.get(restRoot + '/all');
            }
        };
    }]).factory('ContextService', function () {
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