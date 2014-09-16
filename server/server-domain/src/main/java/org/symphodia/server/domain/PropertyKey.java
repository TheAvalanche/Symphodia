package org.symphodia.server.domain;

public enum PropertyKey {

    EDITOR_CONTROLS("[['h1','h2','h3','p','quote','bold','italics','underline'],['ul','ol','indent','outdent'],['html','insertLink','insertImage','insertVideo']]"),
    HOSTNAME("http://localhost:8080/"),
    PAGE_SIZE("10"),
    UPLOADS_PATH("C:\\Development\\wildfly-8.1.0.Final\\standalone\\deployments\\uploaded.war\\");



    private String defaultValue;

    PropertyKey(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
