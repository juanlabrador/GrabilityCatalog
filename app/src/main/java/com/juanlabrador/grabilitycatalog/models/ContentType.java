package com.juanlabrador.grabilitycatalog.models;

import java.io.Serializable;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class ContentType implements Serializable {

    Attribute attributes;

    public ContentType() {
    }

    public Attribute getAttributes() {
        return attributes;
    }
}
