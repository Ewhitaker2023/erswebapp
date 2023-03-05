package com.carpenoctem.model;

import java.util.UUID;

public class Employees extends Accounts {

    public Employees() {
        super();
    }

    public Employees(UUID id, String email, String pass) {
        super(id, email, pass);
    }
}
