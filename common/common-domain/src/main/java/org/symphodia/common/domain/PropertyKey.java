package org.symphodia.common.domain;

public enum PropertyKey {

    HOSTNAME("http://localhost:8080/"),
    PAGE_SIZE("20"),
    UPLOADS_PATH("C:\\Development\\wildfly-8.1.0.Final\\standalone\\deployments\\uploaded.war\\");

    private String defaultValue;

    PropertyKey(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
