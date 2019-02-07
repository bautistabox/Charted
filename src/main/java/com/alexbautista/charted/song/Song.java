package com.alexbautista.charted.song;

public class Song {

    private int id;
    private String title;
    private String artist;
    private Genre genre;
    private int uploader;

    public Song(){}

    public Song(String title, String artist, Genre genre, int uploader) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.uploader = uploader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getUploader() {
        return uploader;
    }

    public void setUploader(int uploader) {
        this.uploader = uploader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
