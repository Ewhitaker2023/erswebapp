package com.carpenoctem.repository;

import com.carpenoctem.model.Employee;
import com.carpenoctem.repository.orm.JDBCRepository;
import java.util.UUID;

public class EmployeeRepository extends JDBCRepository<Employee, UUID> {

}
