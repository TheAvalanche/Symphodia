angular.module('myApp', [
  'ui.router',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers',
  'ui.calendar',
  'ngSanitize'
])
.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function ($stateProvider, $urlRouterProvider, $locationProvider)
{
    $stateProvider
    .state('index', {
        url: '/',
        templateUrl: 'main.html',
        abstract: true
    })
    .state('index.news', {
        url: 'news',
        templateUrl: 'pages/news.html',
        controller  : 'NewsCtrl',
        data : {
            title: 'News',
            breadcrumb  : [{label:"Home",link:""},{label:"News"}]
        }
    })
    .state('index.home', {                      // rename or remove to use an alternative home dashboard
        url: '',
        templateUrl: 'pages/home.html',
        data : {
            title: 'Home'
        }
    })
    .state('login', {
        url: '/login',
        templateUrl: 'login.html'
    });
        
    $urlRouterProvider.otherwise('/');
    $locationProvider.html5Mode(true);
    
}])
.run(['$location', '$rootScope', '$state', 'PropertyService', 'ClientService', 'ContextService',
        function($location, $rootScope, $state, PropertyService, ClientService, ContextService) {
    
    $rootScope.$on('$stateChangeStart', function(event, current, previous){ 
        if (typeof current.data != "undefined") {
            $rootScope.title = current.data.title;
            $rootScope.breadcrumb = current.data.breadcrumb;
        }
    });

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

    ClientService.getClient().success(function (data) {
        ContextService.setClient(data);
        ContextService.setBand(ContextService.getClient().bands[0]);
    });
    
}]);

// init jquery functions and plugins
$.fn.refreshMe = function(opts){
  
    var $this = this,
      defaults = {
        ms:1500,
        selector:"parents('.panel')",
        started:function(){},
        completed:function(){}
    },
    settings = $.extend(defaults, opts);
    
    var container = $(settings.selector);
    var panelToRefresh = container.find('.refresh-container');
    var dataToRefresh = container.find('.refresh-data');
    
    var ms = settings.ms;
    var started = settings.started;		// function before timeout
    var completed = settings.completed;	// function after timeout
    
    var spinIcon;
    if ($this.hasClass("fa")) {
        spinIcon=$this;
    }
    else{
        spinIcon=$this.find('.fa');
    }
    
    var containerCss = {position:"relative"};
    container.css(containerCss);
    
    var overlay = $('<div class="refresh-overlay"><i class="refresh-spinner fa fa-spinner fa-spin fa-5x" style="margin-top:10%;opacity:0.7"></i></div>');
    var css = {
     position:"absolute",
     top:0,
     right:0,
     background:"rgba(200,200,200,0.25)",
     width:"100%",
     height:"100%",
     "text-align":"center",
     "z-index":4
    };
    overlay.css(css);
    
    $this.on('click', function(){
        spinIcon.addClass("fa-spin");
        overlay.insertBefore(panelToRefresh);
        if (dataToRefresh) {
          started(dataToRefresh);
        }
        setTimeout(function(){
          if (dataToRefresh) {
              completed(dataToRefresh);
          }
          overlay.remove();
          spinIcon.removeClass("fa-spin");
        },ms);
        return false;
    }); //click
      
}; /* end refreshMe plugin */


$.fn.pageMe = function(opts){
    var $this = this,
        defaults = {
            perPage: 7,
            showPrevNext: false,
            numbersPerPage: 5,
            hidePageNumbers: false,
            pagerSelector: ".pagination"
        },
        settings = $.extend(defaults, opts);
    
    var listElement = $this;
    var perPage = parseInt(settings.perPage); 
    var children = listElement.children();
    var pager = $(settings.pagerSelector);
    
    if (typeof settings.childSelector!="undefined") {
        children = listElement.find(settings.childSelector);
    }
    
    if (typeof settings.pagerSelector!="undefined") {
        pager = $(settings.pagerSelector);
    }
    
    var numItems = children.size();
    var numPages = Math.ceil(numItems/perPage);

    pager.data("curr",0);
    
    if (settings.showPrevNext){
        $('<li class="disabled"><a href="javascript:;" class="prev_link">«</a></li>').appendTo(pager);
    }
    
    var curr = 0;
    while(numPages > curr && (settings.hidePageNumbers==false)){
       $('<li><a href="javascript:;" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
       curr++;
    }
  
    if (settings.numbersPerPage>1) {
       $('.page_link').hide();
       $('.page_link').slice(pager.data("curr"), settings.numbersPerPage).show();
    }
    
    if (settings.showPrevNext){
       $('<li><a href="javascript:;" class="next_link">»</a></li>').appendTo(pager);
    }
    
    pager.find('.page_link:first').addClass('active');
    if (numPages<=1) {
        pager.find('.next_link').parent().removeClass("disabled");
    }
  	pager.children().eq(1).addClass("active");
    
    children.hide();
    children.slice(0, perPage).show();
    
    pager.find('li .page_link').click(function(){
        var clickedPage = $(this).html().valueOf()-1;
        goTo(clickedPage);
        return false;
    });
    pager.find('li .prev_link').click(function(){
      if ($(this).parent().hasClass("disabled")){
      	return false;
      }
      previous();
      return false;
    });
    pager.find('li .next_link').click(function(){
      if ($(this).parent().hasClass("disabled")){
        return false;
      }
      next();
      return false;
    });
    
    function previous(){
      var goToPage = parseInt(pager.data("curr")) - 1;
      goTo(goToPage);
    }
     
    function next(){
      var goToPage = parseInt(pager.data("curr")) + 1;
      goTo(goToPage);
    }
    
    function goTo(page){
        var startAt = page * perPage,
            endOn = startAt + perPage;
        
        children.css('display','none').slice(startAt, endOn).show();
      
        if (page>=1) {
            pager.find('.prev_link').parent().removeClass("disabled");
        }
        else {
            pager.find('.prev_link').parent().addClass("disabled");
        }
        
        if (page<(numPages-1)) {
            pager.find('.next_link').parent().removeClass("disabled");
        }
        else {
            pager.find('.next_link').parent().addClass("disabled");
        }
        
        pager.data("curr",page);
       
        if (settings.numbersPerPage>1) {
            $('.page_link').slice(page, settings.numbersPerPage+page).show();
        }
        pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active"); 
    }
};
/* end pageMe plugin */


$.fn.aniMe = function(opts){
    var $this = this,
        defaults = {
            aniClass: 'slide-down',
            container: 'window',
            repeat: false
        },
        settings = $.extend(defaults, opts);
    
    var ele = $this;
    var aniClass = settings.aniClass;
    var container = settings.container;
    var repeat = settings.repeat;
    
    if (typeof ele.offset()!="undefined") {
    
        var pos = ele.offset().top;
        
        var topOfWindow = $(container).scrollTop();
        
        if (pos < topOfWindow+500) {
          ele.addClass(aniClass);
          
          if (repeat) {
            setTimeout(function(){
              ele.removeClass(aniClass);
            },1500);
          }
        }
    
    }
};

$(document).ready(function(){
    jQuery.event.props.push('dataTransfer'); //prevent conflict with drag-drop
   
    $('#main-wrapper').scroll(function() {
      $('.list-group, tbody').aniMe({
        aniClass:'pull-down',
        container:'#main-wrapper'
      });
    });
    
    $('[data-toggle=tooltip]').tooltip();
    
});
  
