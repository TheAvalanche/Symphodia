(function () {
    'use strict';

    angular.module('adminApp.directives')
        .directive('sMusicUpload', ['$filter', 'FileService', 'FileUploader', 'ContextService',
            function ($filter, FileService, FileUploader, ContextService) {
                return {
                    restrict: 'E',
                    templateUrl : '/admin/partials/templates/music-upload.html',
                    link: function (scope) {

                        scope.removeQueue = [];
                        scope.addQueue = [];
                        scope.musicGroupList = $filter('GroupItems')(scope.musicList, 4);

                        scope.uploader = new FileUploader({
                            url: '/admin/rest/file/' +  ContextService.getBand().id + '/saveMusic',

                            onAfterAddingFile: function (item) {
                                var uniqueFileName = new Date().getTime().toString();
                                item.alias = uniqueFileName;
                                item.file.name = uniqueFileName;
                                scope.uploader.uploadItem(item);
                            },

                            onCompleteItem: function (item) {
                                scope.addQueue.push(item.file.name);
                                scope.musicList.push(item.file.name);
                                scope.musicGroupList = $filter('GroupItems')(scope.musicList, 4);
                            }
                        });

                        scope.$on("beforeSave", function() {
                            scope.removeQueue.forEach(function (music) {
                                FileService.removeMusic(music);
                            });
                        });

                        scope.$on("beforeCancel", function() {
                            scope.addQueue.forEach(function (music) {
                                FileService.removeMusic(music);
                            });
                        });

                        scope.removeMusic = function (music) {
                            scope.removeQueue.push(music);
                            scope.musicList.splice(scope.musicList.indexOf(music), 1);
                            scope.musicGroupList = $filter('GroupItems')(scope.musicList, 4);
                        };
                    }
                };
            }]);
}());