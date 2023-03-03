package com.carpenoctem.repository.orm;

public class Attribute {

    private Long length;

    public Attribute() {
        super();
    }

    public Attribute(Long length) {
        this.length = length;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
