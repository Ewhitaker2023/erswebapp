package com.carpenoctem.model;

import java.util.UUID;

public class Employee extends Account {

    public Employee() {
        super();
    }

    public Employee(UUID id, String email, String pass) {
        super(id, email, pass);
    }
}
