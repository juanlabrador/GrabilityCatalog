package com.juanlabrador.grabilitycatalog.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class Attribute implements Serializable {

    @SerializedName("im:id")
    String id;
    @SerializedName("im:bundleId")
    String bundleId;
    String height;
    String term;
    String label;
    String rel;
    String type;
    String href;
    String scheme;
    String amount;
    String currency;

    public String getId() {
        return id;
    }

    public String getBundleId() {
        return bundleId;
    }

    public String getHeight() {
        return height;
    }

    public String getTerm() {
        return term;
    }

    public String getLabel() {
        return label;
    }

    public String getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }

    public String getHref() {
        return href;
    }

    public String getScheme() {
        return scheme;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
