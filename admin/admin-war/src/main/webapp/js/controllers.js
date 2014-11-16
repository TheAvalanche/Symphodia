// create the controller and inject Angular's $scope
angular.module('myApp.controllers', [])
.controller('appCtrl', ['$scope', '$window', '$location', '$sce', 'noty', function($scope, $window, $location, $sce, noty){
    $scope.noty = noty;                             // notify service
    
    $scope.date = Date.now();                       // make date accessible to all pages
    
    $scope.reload = function(){                     // force reload
        $window.location.reload(); 
    }
    
    $scope.go = function (path) {                 // redir to a route
        $location.path(path);
    };
    
    $scope.renderHtml = function(html)
    {
        return $sce.trustAsHtml(html);
    };
    
    $scope.isActive = function(path) {           //  monitor path to set active class in menu
      if ($location.path() == path) {
        return true;
      } else {
        return false;
      }
    };
}])
.controller('StartCtrl', ['$scope', 'ContextService', 'ClientService', function ($scope, ContextService, ClientService) {

    var init = function () {
        ClientService.getClient().success(function (data) {
            ContextService.setClient(data);
            ContextService.setBand(ContextService.getClient().bands[0]);
        });
    };

    init();
}])
.controller('NewsCtrl', ['$scope', '$rootScope', '$location', '$anchorScroll', 'asyncScript', 'noty', 'NewsService', 'FileService', 'MessageService', 'ContextService',
        function ($scope, $rootScope, $location, $anchorScroll, asyncScript, noty, NewsService, FileService, MessageService, ContextService) {

    var init = function () {
        $scope.sortingOrder = 'dt';
        $scope.reverse = true;

        $scope.band = ContextService.getBand();

        reloadNews();
    };

    var initEditedNews = function () {
        $scope.editedNews = {
            imageList: [],
            creationDate: new Date()
        };

        $scope.imageList = $scope.editedNews.imageList;
    };

    var reloadNews = function () {
        NewsService.all().success(function (data) {
            $scope.newsList = data;
            initEditedNews();
        });
    };

    $scope.edit = function (news) {
        $location.hash('inputTitle');
        $anchorScroll();
        $('#collapse1').collapse('show');
        $scope.editedNews = news;
        $scope.imageList = $scope.editedNews.imageList;
    };

    $scope.remove = function (news) {
        news.imageList.forEach(function (image) {
            FileService.removeImage(image);
        });
        NewsService.remove(news).success(function () {
            noty.add({type:'warning',title:'Removed...'});
            reloadNews();
        });
    };

    $scope.save = function () {
        NewsService.save($scope.editedNews).success(function () {
            $scope.$broadcast("beforeSave");
            noty.add({type:"info",title:"Saved..."});
            $('#collapse1').collapse('hide');
            reloadNews();
        });
    };

    $scope.cancel = function () {
        $scope.$broadcast("beforeCancel");
        $('#collapse1').collapse('hide');
        reloadNews();
    };

    init();

}])