package com.udemycourse.jpamappingsdemo.dao;

import com.udemycourse.jpamappingsdemo.entity.Course;
import com.udemycourse.jpamappingsdemo.entity.Instructor;
import com.udemycourse.jpamappingsdemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Course findCourseById(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    void deleteCourseById(int id);

    void save(Course course);
}
