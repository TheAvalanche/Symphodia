package org.symphodia.server.domain.band;

import org.symphodia.server.domain.AbstractDomainObject;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Page extends AbstractDomainObject {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PAGE_IMAGE_LIST")
    @Column(name = "IMAGE")
    private List<String> imageList = new ArrayList<>();

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
}
