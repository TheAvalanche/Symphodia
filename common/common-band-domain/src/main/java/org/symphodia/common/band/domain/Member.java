package org.symphodia.common.band.domain;

import org.symphodia.common.domain.AbstractDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "MEMBER")
@NamedQueries({
        @NamedQuery(name = "Member.all", query = "SELECT m FROM Member m ORDER BY m.id DESC"),
        @NamedQuery(name = "Member.count", query = "SELECT COUNT(m) FROM Member m")
})
@SequenceGenerator(name = "MEMBER_SEQ", sequenceName = "MEMBER_SEQ", initialValue = 1000)
public class Member extends AbstractDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @Column(name = "ID")
    private long id;

    @ManyToOne(targetEntity = Band.class)
    @JoinColumn(name="BAND_ID")
    private Band band;

    @Column(name = "NAME", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "SURNAME", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String surname;

    @Column(name = "DESCRIPTION", length = 2048)
    @NotNull
    @Size(min = 1, max = 2048)
    private String description;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    private Date dateOfBirth;

    @Column(name = "INSTRUMENT", length = 255)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Instrument instrument;

    @Column(name = "IMAGE", length = 255)
    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @PrePersist
    public void validate() {
        super.validate();
    }
}
