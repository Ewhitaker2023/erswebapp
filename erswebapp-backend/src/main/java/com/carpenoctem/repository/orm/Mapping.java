package com.carpenoctem.repository.orm;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Mapping {

    private Map<Attribute, Accessor> mapping;

    public Mapping(Map<Attribute, Accessor> mapping) {
        this.mapping = mapping;
    }

    public Map<Attribute, Method> getGetMapping() {
        Map<Attribute, Method> getMapping = new HashMap<Attribute, Method>();
        for (Map.Entry<Attribute, Accessor> entry : mapping.entrySet()) {
            getMapping.put(entry.getKey(), entry.getValue().getGetter());
        }
        return getMapping;
    }

    public Map<Attribute, Method> getSetMapping() {
        Map<Attribute, Method> setMapping = new HashMap<Attribute, Method>();
        for (Map.Entry<Attribute, Accessor> entry : mapping.entrySet()) {
            setMapping.put(entry.getKey(), entry.getValue().getSetter());
        }
        return setMapping;
    }
}
