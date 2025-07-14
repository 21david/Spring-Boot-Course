package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    // Create
    void save(Student theStudent);

    // Read
    Student findById(Integer theId);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    // Update
    void update(Student theStudent);

    // Delete
    void delete(Integer id);

    int deleteAll();
}
