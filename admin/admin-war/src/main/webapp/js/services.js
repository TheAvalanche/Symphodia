'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', []).
value('version', '0.1')
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
})
.factory('ClientService', ['$http', function ($http) {
    var restRoot = '/admin/rest/client';

    return {
        getClient: function() {
            return $http.get(restRoot + '/client');
        }
    };
}])
.factory('NewsService', ['$http', 'ContextService', function ($http, ContextService) {
    var restRoot = '/admin/rest/news';
    var restRootBand = restRoot + '/' + ContextService.getBand().id;

    return {
        all: function() {
            return $http.get(restRootBand + '/all');
        },
        count: function() {
            return $http.get(restRootBand + '/count');
        },
        part: function (offset, max) {
            return $http.get(restRootBand + '/part/' + offset + '/' + max)
        },
        save: function(news) {
            return $http.post(restRootBand + '/save', news);
        },
        remove: function(news) {
            return $http.post(restRootBand + '/remove', news);
        }
    };
}])
.factory('FileService', ['$http', 'ContextService', function ($http, ContextService) {
    var restRoot = '/admin/rest/file';
    var restRootBand = restRoot + '/' + ContextService.getBand().id;

    return {
        removeImage: function(filename) {
            return $http.post(restRootBand + '/removeImage', filename);
        },
        removeMusic: function(filename) {
            return $http.post(restRootBand + '/removeMusic', filename);
        }
    };
}])
.factory('MessageService', ['$timeout', '$rootScope', function ($timeout, $rootScope) {
    var messages = [];

    var messageService = {
        success: function (msg) {
            addMsgWithTimeout({txt: msg, cssClass: 'alert-success'});
        },
        warn: function (msg) {
            addMsgWithTimeout({txt: msg, cssClass: 'alert-warning'});
        },
        error: function (msg) {
            addMsgWithTimeout({txt: msg, cssClass: 'alert-danger'});
        },
        msg: function () {
            return messages;
        },
        clear: function () {
            messages.length = 0;
        },
        removeMessage: function (index) {
            messages.splice(index, 1);
        }
    };

    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        messageService.clear();
    });

    var addMsgWithTimeout = function (message) {
        messages.push(message);
        closeWithTimeout(message);
    };

    var closeWithTimeout = function(message) {
        $timeout(function() {
            if (_.contains(messages, message)) {
                messages.splice(messages.indexOf(message), 1);
            }
        }, 3000);
    };

    return  messageService;
}])
.factory('PropertyService', ['$http', function ($http) {
    var restRoot = '/admin/rest/property';

    return {
        getAll: function() {
            return $http.get(restRoot + '/all');
        },
        save: function(property) {
            return $http.post(restRoot + '/save', property);
        }
    };
}])
.factory('uuid', function() {
    var svc = {
        new: function() {
            function _p8(s) {
                var p = (Math.random().toString(16)+"000000000").substr(2,8);
                return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
            }
            return _p8() + _p8(true) + _p8(true) + _p8();
        },
         
        empty: function() {
          return '00000000-0000-0000-0000-000000000000';
        }
    };
    return svc;
}).
service('asyncScript', ['$window', function($window) {
    
    /* on-demand async script loader using jQuery getScript */
    
    // central config for dependencies - manage libs and versions here..
    var libs = {
        
        flot:        '//cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.min.js',
        flot_resize: 'http://www.flotcharts.org/flot/jquery.flot.resize.js',
        flot_pie:    '//cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.pie.min.js',
        raphael:     '//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js',
        morris:      '//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.0/morris.min.js',
        hotkeys:     'http://mindmup.github.io/bootstrap-wysiwyg/external/jquery.hotkeys.js',
        wysiwyg:     'http://mindmup.github.io/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
        datepicker:  '//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js',
        moment:      '//cdnjs.cloudflare.com/ajax/libs/moment.js/2.6.0/moment.min.js',
        bootstrapdatetimepicker:'//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/js/bootstrap-datetimepicker.min.js',
        jasny:       '//cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js',
        jvectormap:  'http://jvectormap.com/js/jquery-jvectormap-1.2.2.min.js',
        jvectormap_en:'http://jvectormap.com/js/jquery-jvectormap-world-mill-en.js',
        dropzone:    '//cdnjs.cloudflare.com/ajax/libs/dropzone/3.8.4/dropzone.min.js',
        minicolors:  '//cdn.jsdelivr.net/jquery.minicolors/2.1.2/jquery.minicolors.js',
        starr:       '/lib/starrr.js',
        selectpicker:'//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.5.4/bootstrap-select.min.js',
        fullcalandar:'//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.min.js',
        gcal:        '//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/gcal.js',
        angular_calendar:'//cdnjs.cloudflare.com/ajax/libs/angular-ui-calendar/0.8.0/calendar.min.js'
        
    };
    
    return {

        load: function(name,cb) {
            
            /* async load if not already attached to $window */
            if (!$window["loader_"+name]) {
                // load it
                $.getScript(libs[name],function(){
                    $window["loader_"+name] = true;
                    cb();
                });
            }
            else {
                // script is already available to window - do callback
                cb();
            }
        }
    }

}]).
service('googleMapsSvc', ['$window', function($window) {
    
    var gAddress, gMapElement, gOptions, gUseSv;

    function initialize(address, mapElement, options, useStreetView) {

        var google = $window.google;
        var geocoder = new $window.google.maps.Geocoder();
        var map, center, mapOptions = {
            zoom: 13,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        $.extend(mapOptions, options); // merge the defaults with options passed in
        
        map = new google.maps.Map(document.getElementById(mapElement),mapOptions);
        
        if (geocoder) {
          geocoder.geocode({'address': address}, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
              
                /* find and set the center */
                center = results[0].geometry.location;
                map.setCenter(center);
                
                /* wait for the map to be ready */
                google.maps.event.addListener(map, 'idle', function()
                {
                    if (useStreetView && center.lat()) {
                        var latlng = new google.maps.LatLng(center.lat(), center.lng());
                        var mapOptions = {
                             position: latlng,
                             pov: {
                              heading: 165,
                              pitch: 0
                            },
                            zoom : 1
                        };
                        map = new google.maps.StreetViewPanorama(document.getElementById(mapElement),mapOptions);
                        map.setVisible(true);
                    }
                });
                
                /* uncomment this to display an InfoWindow
                new google.maps.InfoWindow(
                    {
                      content: address,
                      map: map,
                      position: results[0].geometry.location,
                    });
                */
                
                /* display a marker */
                new google.maps.Marker({
                    position: results[0].geometry.location,
                    map: map, 
                    title:address
                });
            }
          });
        } // if geocoder
    }
    
    /* init map must be attached to the $window so that google callback can access it */
    $window.initializeMap = function () {
        initialize(gAddress, gMapElement, gOptions, gUseSv);
    }
    
    return {
        init: function(address, mapElement, options, useSv) {
            
            gAddress = address;
            gMapElement = mapElement;
            gOptions = options;
            gUseSv = useSv||false;
            
            /* load google maps if not already attached to $window */
            $window.initializeMap();
           
        }
    } // return
}]).
service('noty', ['$rootScope', function($rootScope) {
    var queue = $rootScope.queue||[];
     
    return {
        queue: queue,
        add: function( item ) {
            //setTimeout(function(){
                queue.push(item);
                setTimeout(function(){
                    // remove the alert after 5 sec using jq
                    var ele = $('.alerts .alert').eq(0);
                    ele.fadeOut(3000,function(){
                        ele.remove();
                    });
                    queue.shift();
                    $rootScope.$apply();
                },5000);
            //},150);
        },
        pop: function(){
            return queue.pop();       
        }
    };
}]);
