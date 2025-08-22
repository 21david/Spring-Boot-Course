package com.udemycourse.jpamappingsdemo.dao;

import com.udemycourse.jpamappingsdemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
