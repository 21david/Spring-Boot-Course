package com.udemycourse.jpamappingsdemo.dao;

import com.udemycourse.jpamappingsdemo.entity.Course;
import com.udemycourse.jpamappingsdemo.entity.Instructor;
import com.udemycourse.jpamappingsdemo.entity.InstructorDetail;
import com.udemycourse.jpamappingsdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        // Through cascading, this also saves the instructor detail as a row in the
        // instructor_detail table
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);  // also retrieved InstructorDetail because @OneToOne is eager by default
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        Instructor toDelete = findInstructorById(id);

        // Add code to break association of all this instructor's courses
        List<Course> courses = toDelete.getCourses();
        for (Course course : courses)  // For each triggers a SELECT to initialize the lazy collection
            course.setInstructor(null);  // Marks each Course dirty; on flush, Hibernate issues UPDATEs setting instructor_id = NULL

        entityManager.remove(toDelete); // causes query to delete instructor detail also, because of CascadeType.ALL
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {

        // Because of CascadeType.ALL in InstructorDetail, this also gets any associated Instructor in the database
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail det = findInstructorDetailById(id);

        // remove the associated object reference to break the bi-directional link
        det.getInstructor().setInstructorDetail(null);

        // Through cascading, this also deletes the instructor
        entityManager.remove(det);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        // This actually does a left join
        TypedQuery<Instructor> query = entityManager.createQuery("""
                SELECT i FROM Instructor i
                JOIN FETCH i.courses
                JOIN FETCH i.instructorDetail
                WHERE i.id = :data
        """, Instructor.class);

        query.setParameter("data", id);

        // this query will return the instructor object with the courses already set, even if courses is set to LAZY loading
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        // This will save the course and its associated reviews because of CascadeType.ALL
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("""
             SELECT c from Course c
             JOIN FETCH c.reviews
             WHERE c.id = :id
        """, Course.class);

        query.setParameter("id", id);

        // The returned course will automatically have its list of reviews correctly populated
        Course result = query.getSingleResult();
        System.out.println("findCourseAndReviewsByCourseId() got the course with id " + id + ": " + result);
        System.out.println("findCourseAndReviewsByCourseId() got the course reviews with id " + id + ": " + result.getReviews());
        return result;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("""
            SELECT c from Course c
            JOIN FETCH c.students
            WHERE c.id = :id
        """, Course.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("""
            SELECT s from Student s
            JOIN FETCH s.courses
            WHERE s.id = :id
        """, Student.class);

        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}