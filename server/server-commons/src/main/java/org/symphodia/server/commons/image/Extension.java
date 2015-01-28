package org.symphodia.server.commons.image;

public enum Extension {

    PNG(".png"),
    PNG_SMALL("_s.png"),
    PNG_SMALL_WIDE("_sw.png"),
    PNG_MEDIUM("_m.png"),
    PNG_MEDIUM_WIDE("_mw.png"),
    MP3(".mp3");

    private String extension;

    Extension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
