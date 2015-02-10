(function () {
    'use strict';

    angular.module('adminApp.directives')
        .directive('sLogoUpload', ['$filter', 'FileService', 'FileUploader', 'ContextService',
            function ($filter, FileService, FileUploader, ContextService) {
            return {
                restrict: 'E',
                templateUrl : '/admin/partials/templates/logo-upload.html',
                link: function (scope) {

                    scope.removeQueue = [];
                    scope.addQueue = [];
                    scope.logoGroupList = $filter('GroupItems')(scope.logoList, 4);

                    scope.uploader = new FileUploader({
                        url: '/admin/rest/file/' +  ContextService.getBand().id + '/saveLogo',

                        onAfterAddingFile: function (item) {
                            scope.uploader.uploadItem(item);
                        },

                        onCompleteItem: function (item, response) {
                            item.file.name = response.name;
                            scope.addQueue.push(item.file.name);
                            scope.logoList.push(item.file.name);
                            scope.logoGroupList = $filter('GroupItems')(scope.logoList, 4);
                        }
                    });

                    scope.$on("beforeSave", function() {
                        scope.removeQueue.forEach(function (logo) {
                            FileService.removeLogo(logo);
                        });
                    });

                    scope.$on("beforeCancel", function() {
                        scope.addQueue.forEach(function (logo) {
                            FileService.removeLogo(logo);
                        });
                    });

                    scope.removeLogo = function (logo) {
                        scope.removeQueue.push(logo);
                        scope.logoList.splice(scope.logoList.indexOf(logo), 1);
                        scope.logoGroupList = $filter('GroupItems')(scope.logoList, 4);
                    };
                }
            };
        }]);
}());