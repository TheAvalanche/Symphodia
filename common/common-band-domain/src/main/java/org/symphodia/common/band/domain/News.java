package org.symphodia.common.band.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "NEWS")
@NamedQueries({
        @NamedQuery(name = "News.getAll", query = "SELECT n FROM News n")
})
public class News extends AbstractDomainObject {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "TITLE")
    @NotNull
    private String title;

    @Column(name = "CONTENT")
    @NotNull
    private String content;

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
}
