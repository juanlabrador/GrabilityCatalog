package com.juanlabrador.grabilitycatalog.models;

import java.io.Serializable;

/**
 * Created by juanlabrador on 25/11/16.
 */

public class Category implements Serializable {

    Attribute attributes;

    public Category() {
    }

    public Attribute getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Category) {
            if (attributes.getId().equals(((Category) obj).getAttributes().getId())) {
                return true;
            }
        } return false;
    }
}
