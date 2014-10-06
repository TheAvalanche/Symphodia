package org.symphodia.server.commons.image;

/**
 * Created by alekkart on 2014.10.06..
 */
public enum Extension {

    PNG(".png"),
    PNG_SMALL("_s.png"),
    MP3(".mp3");

    private String extension;

    Extension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
