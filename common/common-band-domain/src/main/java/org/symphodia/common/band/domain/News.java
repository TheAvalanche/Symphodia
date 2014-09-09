package org.symphodia.common.band.domain;

import org.symphodia.common.domain.AbstractDomainObject;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NEWS")
@NamedQueries({
        @NamedQuery(name = "News.all", query = "SELECT n FROM News n ORDER BY n.id DESC"),
        @NamedQuery(name = "News.count", query = "SELECT COUNT(n) FROM News n")
})
@SequenceGenerator(name = "NEWS_SEQ", sequenceName = "NEWS_SEQ", initialValue = 100000)
public class News extends AbstractDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATION_DATE")
    @NotNull
    @Past
    private Date creationDate;

    @Column(name = "TITLE", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @Column(name = "CONTENT", length = 8096)
    @NotNull
    @Size(min = 1, max = 8096)
    private String content;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "NEWS_IMAGE_LIST")
    @Column(name = "IMAGE")
    private List<String> imageList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    @PrePersist
    public void validate() {
        super.validate();
    }
}
