package org.symphodia.server.domain.band;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.symphodia.server.domain.AbstractDomainObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SONG")
@NamedQueries({
        @NamedQuery(name = "Song.allByAlbum", query = "SELECT s FROM Song s WHERE s.album.id = :albumId ORDER BY s.id DESC"),
})
@SequenceGenerator(name = "SONG_SEQ", sequenceName = "SONG_SEQ", initialValue = 2000000)
public class Song extends AbstractDomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SONG_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name="ALBUM_ID")
    @JsonIgnore
    private Album album;

    @Column(name = "ORDER_NUMBER")
    @NotNull
    private Long orderNumber;

    @Column(name = "TITLE", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @Column(name = "WORDS_AUTHOR", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String wordsAuthor;

    @Column(name = "MUSIC_AUTHOR", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String musicAuthor;

    @Column(name = "TEXT", length = 4096)
    @Size(min = 1, max = 4096)
    private String text;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SONG_MUSIC_LIST")
    @Column(name = "MUSIC")
    private List<String> musicList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWordsAuthor() {
        return wordsAuthor;
    }

    public void setWordsAuthor(String wordsAuthor) {
        this.wordsAuthor = wordsAuthor;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<String> musicList) {
        this.musicList = musicList;
    }

    public void addMusic(String music) {
        this.musicList.add(music);
    }

    public void removeMusic(String music) {
        this.musicList.remove(music);
    }

    @PrePersist
    public void validate() {
        super.validate();
    }
}
