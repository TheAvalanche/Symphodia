// Common configuration files with defaults plus overrides from environment vars
module.exports = {
    // The address of a running selenium server.
    seleniumAddress:
        (process.env.SELENIUM_URL || 'http://localhost:4444/wd/hub'),

    // Capabilities to be passed to the webdriver instance.
    capabilities: {
        'browserName':
            (process.env.TEST_BROWSER_NAME    || 'firefox'),
        'version':
            (process.env.TEST_BROWSER_VERSION || 'ANY')
    },

    adminBaseUrl:
        'http://' + (process.env.HTTP_HOST || 'localhost') +
        ':' + (process.env.HTTP_PORT || '8080') + '/admin/'

};