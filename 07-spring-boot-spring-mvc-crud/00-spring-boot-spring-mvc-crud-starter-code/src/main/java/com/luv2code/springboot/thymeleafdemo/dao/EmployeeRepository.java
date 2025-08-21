package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // custom method to sort by last name
    // Spring Data JPA automatically parses this method name and knows how to do it exactly (sort by last name)
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    public List<Employee> findAllByOrderByLastNameAsc();
}
