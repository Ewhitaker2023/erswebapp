package com.carpenoctem.service;

import com.carpenoctem.model.Employee;
import com.carpenoctem.repository.EmployeeRepository;

public class EmployeeService {

    public Employee save(Employee employee) {
        EmployeeRepository repository = new EmployeeRepository();
        return repository.save(employee);
    }
}
