package org.symphodia.server.domain.band;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class SocialData {

    @Column(name = "FACEBOOK_LINK", length = 255)
    @Size(min = 1, max = 255)
    private String facebookLink;

    @Column(name = "YOUTUBE_LINK", length = 255)
    @Size(min = 1, max = 255)
    private String youtubeLink;

    @Column(name = "TWITTER_LINK", length = 255)
    @Size(min = 1, max = 255)
    private String twitterLink;

    @Column(name = "VK_LINK", length = 255)
    @Size(min = 1, max = 255)
    private String vkLink;

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getVkLink() {
        return vkLink;
    }

    public void setVkLink(String vkLink) {
        this.vkLink = vkLink;
    }
}
