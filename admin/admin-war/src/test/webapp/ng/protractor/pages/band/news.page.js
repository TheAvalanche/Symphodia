(function () {
    'use strict';

    module.exports = {

        addNews: function () {
            element(by.buttonText('Add News')).click();
        },
        updateFirstNews: function () {
            browser.actions().mouseMove(element.all(by.repeater('news in newsList')).first()).perform();
            element.all(by.css(".glyphicon-pencil")).get(0).click();
        },
        removeFirstNews: function () {
            browser.actions().mouseMove(element.all(by.repeater('news in newsList')).first()).perform();
            element.all(by.css(".glyphicon-trash")).get(0).click();
        },
        saveNews: function () {
            element(by.buttonText('Save')).click();
        },
        setTitle: function (title) {
            element(by.model('news.title')).clear();
            element(by.model('news.title')).sendKeys(title);
        },
        setContent: function(content) {
            element(by.model('news.content')).all(by.model('html')).get(0).clear();
            element(by.model('news.content')).all(by.model('html')).get(0).sendKeys(content);
        },
        setImage: function(image) {
            element(by.css('input[type="file"]')).sendKeys(image);
        }
    };
}());