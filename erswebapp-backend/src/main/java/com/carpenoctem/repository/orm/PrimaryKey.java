package com.carpenoctem.repository.orm;

public class PrimaryKey extends Attribute {
    private Short keySequence;

    public PrimaryKey(String name, Class<?> type) {
        super(name, type);
    }

    public PrimaryKey(Short keySequence, Integer position, String name, Class<?> type, Boolean isAutoIncrement,
            Boolean isNullable) {
        super(position, name, type, isAutoIncrement, isNullable);
        this.keySequence = keySequence;
    }

    public Short getKeySequence() {
        return keySequence;
    }

    public void setKeySequence(Short keySequence) {
        this.keySequence = keySequence;
    }

}
