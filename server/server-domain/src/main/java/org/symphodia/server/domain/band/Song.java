package org.symphodia.server.domain.band;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SONG")
@SequenceGenerator(name = "SONG_SEQ", sequenceName = "SONG_SEQ", initialValue = 2000000)
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SONG_SEQ")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name="ALBUM_ID")
    @JsonIgnore
    private Album album;

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

    @Column(name = "text", length = 4096)
    @Size(min = 1, max = 4096)
    private String text;

    @Column(name = "FILE", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String file;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
