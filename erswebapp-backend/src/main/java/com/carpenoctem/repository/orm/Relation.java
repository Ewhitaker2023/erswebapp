package com.carpenoctem.repository.orm;

import java.util.List;

public class Relation {

    private List<Attribute> attributes;

    public Relation() {
        super();
    }

    public Relation(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
