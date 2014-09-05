(function () {
    'use strict';

    module.exports = {

        addNews: function () {
            element(by.buttonText('Add News')).click();
        },
        updateFirstNews: function () {
            browser.actions().mouseMove(element.all(by.repeater('news in newsList')).first()).perform();
            element(by.css(".glyphicon-pencil")).click();
        },
        removeFirstNews: function () {
            browser.actions().mouseMove(element.all(by.repeater('news in newsList')).first()).perform();
            element(by.css(".glyphicon-trash")).click();
        },
        saveNews: function () {
            element(by.buttonText('Save')).click();
        },
        setTitle: function (title) {
            element(by.model('news.title')).clear();
            element(by.model('news.title')).sendKeys(title);
        },
        setContent: function(content) {
            element(by.model('news.content')).element(by.model('html')).clear();
            element(by.model('news.content')).element(by.model('html')).sendKeys(content);
        }
    };
}());