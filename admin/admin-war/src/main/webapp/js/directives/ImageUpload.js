(function () {
    'use strict';

    angular.module('adminApp.directives')
        .directive('sImageUpload', ['$filter', 'FileService', 'FileUploader', 'ContextService',
            function ($filter, FileService, FileUploader, ContextService) {
            return {
                restrict: 'E',
                templateUrl : '/admin/partials/templates/image-upload.html',
                link: function (scope) {

                    scope.removeQueue = [];
                    scope.addQueue = [];
                    scope.imageGroupList = $filter('GroupItems')(scope.imageList, 4);

                    scope.uploader = new FileUploader({
                        url: '/admin/rest/file/' +  ContextService.getBand().id + '/saveImage',

                        onAfterAddingFile: function (item) {
                            scope.uploader.uploadItem(item);
                        },

                        onCompleteItem: function (item, response) {
                            item.file.name = response.name;
                            scope.addQueue.push(item.file.name);
                            scope.imageList.push(item.file.name);
                            scope.imageGroupList = $filter('GroupItems')(scope.imageList, 4);
                        }
                    });

                    scope.$on("beforeSave", function() {
                        scope.removeQueue.forEach(function (image) {
                            FileService.removeImage(image);
                        });
                    });

                    scope.$on("beforeCancel", function() {
                        scope.addQueue.forEach(function (image) {
                            FileService.removeImage(image);
                        });
                    });

                    scope.removeImage = function (image) {
                        scope.removeQueue.push(image);
                        scope.imageList.splice(scope.imageList.indexOf(image), 1);
                        scope.imageGroupList = $filter('GroupItems')(scope.imageList, 4);
                    };
                }
            };
        }]);
}());