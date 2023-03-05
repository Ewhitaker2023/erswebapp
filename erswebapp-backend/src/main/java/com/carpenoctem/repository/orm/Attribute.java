package com.carpenoctem.repository.orm;

public class Attribute {

    private Integer position;
    private String name;
    private Class<?> type;
    private Boolean isAutoIncrement;
    private Boolean isNullable;

    public Attribute(String name, Class<?> type) {
        this.position = null;
        this.name = name;
        this.type = type;
        this.isAutoIncrement = null;
        this.isNullable = null;
    }

    public Attribute(Integer position, String name, Class<?> type, Boolean isAutoIncrement, Boolean isNullable) {
        this.position = position;
        this.name = name;
        this.type = type;
        this.isAutoIncrement = isAutoIncrement;
        this.isNullable = isNullable;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Boolean getIsAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(Boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attribute))
            return false;
        Attribute other = (Attribute) o;
        if (!other.canEqual((Object) this))
            return false;
        if (this.getName() == null ? other.getName() != null : !this.getName().equals(other.getName()))
            return false;
        if (this.getType() == null ? other.getType() != null : !this.getType().equals(other.getType()))
            return false;
        return true;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Attribute;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = (result * PRIME) + (this.name == null ? 43 : this.name.hashCode());
        result = (result * PRIME) + (this.type == null ? 31 : this.type.hashCode());
        return result;
    }
}
