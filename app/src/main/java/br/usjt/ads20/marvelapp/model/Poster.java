package br.usjt.ads20.marvelapp.model;

import android.graphics.Bitmap;

public class Poster implements Comparable<Poster>{
    private int id;
    private String title;
    private Bitmap poster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    @Override
    public int compareTo(Poster poster) {
        return title.compareTo(poster.getTitle());
    }
}