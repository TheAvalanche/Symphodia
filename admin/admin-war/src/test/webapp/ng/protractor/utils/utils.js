(function () {
    "use strict";

    module.exports = {

        navigateTo: function() {
            for (var arg = 0; arg < arguments.length; ++ arg) {
                element(by.id(arguments[arg])).click();
            }
        }
    };
}());
