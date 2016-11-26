package com.juanlabrador.grabilitycatalog.models;

import java.util.List;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class Feed {

    Author author;
    Uri uri;
    List<Entry> entry;
    Updated updated;
    Right rights;
    Title title;
    Icon icon;
    List<Link> link;
    Identity id;

    public Feed() {
    }

    public Identity getId() {
        return id;
    }

    public Updated getUpdated() {
        return updated;
    }

    public Right getRights() {
        return rights;
    }

    public Title getTitle() {
        return title;
    }

    public Icon getIcon() {
        return icon;
    }

    public List<Link> getLink() {
        return link;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
