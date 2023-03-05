package com.carpenoctem.repository.orm;

public class ObjectRelationMapper {

    private Class<?> type;
    private String classname;
    private Relation relation;
    private Mapping mapping;

    public ObjectRelationMapper() {
        super();
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

}
