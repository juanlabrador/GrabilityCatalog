package com.juanlabrador.grabilitycatalog.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class Entry implements Serializable {

    @SerializedName("im:name")
    Name name;
    @SerializedName("im:image")
    List<Image> images;
    Summary summary;
    @SerializedName("im:price")
    Price price;
    @SerializedName("im:contentType")
    ContentType contentType;
    Right rights;
    Title title;
    Link link;
    Identity id;
    @SerializedName("im:artist")
    Artist artist;
    Category category;
    @SerializedName("im:releaseDate")
    ReleaseDate releaseDate;

    public Entry() {
    }

    public Name getName() {
        return name;
    }

    public List<Image> getImages() {
        return images;
    }

    public Summary getSummary() {
        return summary;
    }

    public Price getPrice() {
        return price;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Right getRights() {
        return rights;
    }

    public Title getTitle() {
        return title;
    }

    public Link getLink() {
        return link;
    }

    public Identity getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public Category getCategory() {
        return category;
    }

    public ReleaseDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entry) {
            if (getId().getAttributes().getId().equals(((Entry) obj).getId().getAttributes().getId())) {
                return true;
            }
        } return false;
    }
}
