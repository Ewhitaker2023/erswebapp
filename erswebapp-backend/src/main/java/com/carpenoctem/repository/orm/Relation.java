package com.carpenoctem.repository.orm;

import java.util.List;

public class Relation {

    private List<Attribute> attributes;
    private List<PrimaryKey> primaryKeys;

    public Relation(List<Attribute> attributes, List<PrimaryKey> primaryKeys) {
        this.attributes = attributes;
        this.primaryKeys = primaryKeys;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<PrimaryKey> getPrimaryKeys() {
        return primaryKeys;
    }
}
