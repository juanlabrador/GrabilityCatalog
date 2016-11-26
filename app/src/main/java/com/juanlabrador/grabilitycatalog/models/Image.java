package com.juanlabrador.grabilitycatalog.models;

import java.io.Serializable;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class Image implements Serializable {

    String label;
    Attribute attributes;

    public Image() {
    }

    public String getLabel() {
        return label;
    }

    public Attribute getAttributes() {
        return attributes;
    }
}
