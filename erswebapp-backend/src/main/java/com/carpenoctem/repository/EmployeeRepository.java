package com.carpenoctem.repository;

import com.carpenoctem.model.Employees;
import com.carpenoctem.repository.orm.JDBCRepository;

public class EmployeeRepository extends JDBCRepository<Employees> {

    public EmployeeRepository(Class<Employees> type) {
        super(type);
    }

}
