package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Employee = entity or database table, Integer = primary key (int id)
// This is the easier alternative to our previous DAO interface and implementation
// @RepositoryRestResource(path="members")  // expose at endpoint /magic-api/members instead of /magic-api/employees
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // All the methods, findAll(), save(), deleteById(), etc, are automatically given to us
    // just by extending JpaRepository!
    // Also includes @Transactional with these methods, so we don't have to
}
