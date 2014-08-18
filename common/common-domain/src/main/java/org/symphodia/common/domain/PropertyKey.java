package org.symphodia.common.domain;

public enum PropertyKey {

    HOSTNAME("http://localhost:8080/"),
    UPLOADS_PATH("C:\\Development\\wildfly-8.1.0.Final\\standalone\\deployments\\uploaded.war\\"),
    FACEBOOK_APP_ID(""),
    FACEBOOK_APP_SECRET(""),
    FACEBOOK_ACCESS_TOKEN("");

    private String defaultValue;

    PropertyKey(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
