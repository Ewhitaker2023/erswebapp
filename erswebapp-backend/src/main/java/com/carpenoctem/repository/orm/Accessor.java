package com.carpenoctem.repository.orm;

import java.lang.reflect.Method;

public class Accessor {

    private Method getter;
    private Method setter;

    public Accessor() {
        super();
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

}
