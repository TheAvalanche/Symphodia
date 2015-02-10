package org.symphodia.server.domain.band;

import org.symphodia.server.domain.AbstractDomainObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Page extends AbstractDomainObject {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PAGE_LOGO_LIST")
    @Column(name = "LOGO")
    private List<String> logoList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PAGE_IMAGE_LIST")
    @Column(name = "IMAGE")
    private List<String> imageList = new ArrayList<>();

    @Embedded
    private SocialData socialData = new SocialData();

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public void addImage(String image) {
        this.imageList.add(image);
    }

    public void removeImage(String image) {
        this.imageList.remove(image);
    }

    public List<String> getLogoList() {
        return logoList;
    }

    public void setLogoList(List<String> logoList) {
        this.logoList = logoList;
    }

    public void addLogo(String logo) {
        this.logoList.add(logo);
    }

    public void removeLogo(String logo) {
        this.logoList.remove(logo);
    }

    public SocialData getSocialData() {
        return socialData;
    }

    public void setSocialData(SocialData socialData) {
        this.socialData = socialData;
    }
}
