(function () {
    "use strict";

    var loginPage = require('../pages/login.page.js');
    var newsPage = require('../pages/band/news.page.js');
    var utils = require('../utils/utils.js');

    describe('login', function() {
        it('should login', function() {
            loginPage.loginWithDefaults();
        });
    });

    describe('navigateToNewsPage', function() {
        it('should navigate to news page', function() {
            utils.navigateTo("menu-band", "menu-band-news");
        });
    });

    describe('addNews', function() {
        it('should add news', function() {
            newsPage.addNews();
            newsPage.setTitle("Test Title");
            newsPage.setContent("Test Content");
            newsPage.setImage('C:/Projects/Symphodia/admin/admin-war/src/test/resources/test.png');
            newsPage.saveNews();

            expect(element.all(by.repeater('news in newsList').column('news.title')).first().getText()).toEqual("Test Title");
            expect(element.all(by.repeater('news in newsList').column('news.content')).first().getText()).toEqual("Test Content")
        });
    });

    describe('updateNews', function() {
        it('should update news', function() {
            newsPage.updateFirstNews();
            newsPage.setTitle("Test Title Updated");
            newsPage.setContent("Test Content Updated");
            newsPage.setImage('C:/Projects/Symphodia/admin/admin-war/src/test/resources/test2.png');
            newsPage.saveNews();

            expect(element.all(by.repeater('news in newsList').column('news.title')).first().getText()).toEqual("Test Title Updated");
            expect(element.all(by.repeater('news in newsList').column('news.content')).first().getText()).toEqual("Test Content Updated");
        });
    });

    describe('removeNews', function() {
        it('should remove news', function() {
            newsPage.removeFirstNews();
        });
    });

}());