package com.juanlabrador.grabilitycatalog.models;

import java.io.Serializable;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class Price implements Serializable {

    String label;
    Attribute attributes;

    public Price() {
    }

    public String getLabel() {
        return label;
    }

    public Attribute getAttributes() {
        return attributes;
    }
}
