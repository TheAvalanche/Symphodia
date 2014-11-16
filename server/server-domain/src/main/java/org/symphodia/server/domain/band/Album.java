package org.symphodia.server.domain.band;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.symphodia.server.domain.AbstractDomainObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ALBUM")
@NamedQueries({
        @NamedQuery(name = "Album.allByBand", query = "SELECT a FROM Album a WHERE a.band.id = :bandId ORDER BY a.id DESC"),
})
@SequenceGenerator(name = "ALBUM_SEQ", sequenceName = "ALBUM_SEQ", initialValue = 200000)
public class Album extends AbstractDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALBUM_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Band.class)
    @JoinColumn(name="BAND_ID")
    @JsonIgnore
    private Band band;

    @Column(name = "TITLE", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date creationDate;

    @Column(name = "DESCRIPTION", length = 4096)
    @Size(min = 1, max = 4096)
    private String description;

    @Column(name = "ALBUM_TYPE", length = 255)
    @Enumerated(EnumType.STRING)
    @NotNull
    private AlbumType albumType = AlbumType.FULL;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private List<Song> songList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ALBUM_IMAGE_LIST")
    @Column(name = "IMAGE")
    private List<String> imageList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AlbumType getAlbumType() {
        return albumType;
    }

    public void setAlbumType(AlbumType albumType) {
        this.albumType = albumType;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void addSong(Song song) {
        this.songList.add(song);
    }

    public void removeSong(Song song) {
        this.songList.remove(song);
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
