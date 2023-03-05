package com.carpenoctem.service;

import java.util.List;

import com.carpenoctem.model.Employees;
import com.carpenoctem.repository.EmployeeRepository;

public class EmployeeService {

    public Employees selectByPk(Employees employee) {
        EmployeeRepository repository = new EmployeeRepository(Employees.class);
        return repository.selectByPk(employee).get();
    }

    public List<Employees> selectAll() {
        EmployeeRepository repository = new EmployeeRepository(Employees.class);
        return repository.selectAll();
    }

    public List<Employees> selectAllByPk(Iterable<Employees> employees) {
        EmployeeRepository repository = new EmployeeRepository(Employees.class);
        return repository.selectAllByPk(employees);
    }
}
