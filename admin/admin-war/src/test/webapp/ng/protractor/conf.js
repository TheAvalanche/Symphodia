/*global exports, require */

var env = require('./environment.js');

exports.config = {
    // The address of a running selenium server.
    seleniumAddress: env.seleniumAddress,

    suites: {
        band_news: 'spec/band-news-spec.js'
    },

    capabilities: env.capabilities,

    baseUrl: env.adminBaseUrl,

    params: {
        login: {
            username: 'admin',
            password: 'admin'
        }
    },

    onPrepare: function() {

    }
};
