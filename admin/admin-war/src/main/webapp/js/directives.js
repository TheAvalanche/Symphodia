'use strict';

/* Directives */
angular.module('myApp.directives', [])
    .directive('appVersion', ['version', function (version) {
        return function (scope, elm, attrs) {
            elm.text(version);
        };
    }])
    .directive('header', function () {
        return {
            restrict: 'A',
            transclude: true,
            templateUrl: 'partials/header.html'
        }
    })
    .directive('footer', function () {
        return {
            restrict: 'A',
            transclude: true,
            templateUrl: 'partials/footer.html'
        }
    })
    .directive('sidebar', function () {
        return {
            restrict: 'A',
            transclude: true,
            templateUrl: 'partials/sidebar.html'
        }
    })
    .directive('breadcrumb', function () {
        return {
            restrict: 'A',
            replace: true,
            templateUrl: 'partials/breadcrumb.html'
        }
    })
    .directive('menuItems', function () {
        return {
            restrict: 'A',
            transclude: true,
            templateUrl: 'partials/menu-items.html'
        }
    })
    .directive('menuItemsSm', function () {
        return {
            restrict: 'A',
            transclude: true,
            templateUrl: 'partials/menu-items-sm.html'
        }
    })
    .directive('dropZone', ['ContextService', 'FileService', function (ContextService, FileService) {
        return function (scope, element, attrs) {

            scope.removeQueue = [];
            scope.addQueue = [];

            var init = function () {
                dropZone.removeAllFiles();
                scope.removeQueue.length = 0;
                scope.addQueue.length = 0;
            };

            var dropZone = new Dropzone(element[0], {
                url: '/admin/rest/file/' + ContextService.getBand().id + '/saveImage',
                maxFilesize: 100,
                maxThumbnailFilesize: 5,
                thumbnailWidth: 180,
                thumbnailHeight: 180,
                previewsContainer: ".previews",
                addRemoveLinks: true,
                init: function () {
                    this.on('success', function (file, json) {
                        file.imageName = json.name;
                        scope.addQueue.push(file.imageName);
                        scope.imageList.push(file.imageName);
                        $(file.previewElement).find('img').attr('src', 'http://localhost:8080/uploaded/' + ContextService.getBand().id + '/' + file.imageName + '_s.png');
                    });
                    this.on('removedfile', function (file) {
                        scope.removeQueue.push(file.imageName);
                        scope.imageList.splice(scope.imageList.indexOf(file.imageName), 1);
                    });
                }
            });
            scope.$on("beforeCancel", function () {
                scope.addQueue.forEach(function (image) {
                    FileService.removeImage(image);
                });
                init();
            });

            scope.$on("beforeSave", function () {
                scope.removeQueue.forEach(function (image) {
                    FileService.removeImage(image);
                });
                init();
            });

            scope.$watch('imageList', function (imageList) {
                $.each(imageList, function (key, value) {

                    var mockFile = { imageName: value };

                    dropZone.options.addedfile.call(dropZone, mockFile);
                    dropZone.options.thumbnail.call(dropZone, mockFile, 'http://localhost:8080/uploaded/' + ContextService.getBand().id + '/' + value + '_s.png');

                });
            });

            init();
        };
    }])
    .directive('textEditor', ['asyncScript', function (asyncScript) {
        return {
            scope: {
                source: "="
            },
            templateUrl: "partials/texteditor.html",
            link: function (scope, element, attributes, controller) {
                $('#editor').wysiwyg();
                $('#editor').cleanHtml();
            }
        };
    }]);