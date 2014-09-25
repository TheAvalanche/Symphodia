package org.symphodia.server.domain.band;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.symphodia.server.domain.AbstractDomainObject;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BAND")
@NamedQueries({
        @NamedQuery(name = "Band.all", query = "SELECT b FROM Band b ORDER BY b.id DESC")
})
@SequenceGenerator(name = "BAND_SEQ", sequenceName = "BAND_SEQ", initialValue = 100000)
public class Band extends AbstractDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BAND_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "DESCRIPTION", length = 2048)
    @Size(min = 1, max = 2048)
    private String description;

    @OneToMany(mappedBy = "band")
    @JsonIgnore
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "band")
    @JsonIgnore
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy = "band")
    @JsonIgnore
    private List<Album> albumList = new ArrayList<>();

    @Embedded
    private Page page;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @PrePersist
    public void validate() {
        super.validate();
    }
}
