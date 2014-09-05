/*global browser, by, element, protractor*/

(function () {
    'use strict';

    module.exports = {

        init: function() {
            browser.get(browser.baseUrl);
        },

        setUsername: function (userName) {
            element(by.id('username')).sendKeys(userName);
        },
        setPassword: function (password) {
            element(by.id('password')).sendKeys(password);
        },
        login: function () {
            element(by.id('login')).click();
        },
        loginWithDefaults: function() {
            this.init();
            this.setUsername(browser.params.login.username);
            this.setPassword(browser.params.login.password);
            this.login();
        }
    };
}());