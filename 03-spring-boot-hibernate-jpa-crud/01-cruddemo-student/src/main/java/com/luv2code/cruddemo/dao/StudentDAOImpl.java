package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // Define field for entity manager
    private EntityManager entityManager;


    // Inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // CREATE:

    // Implement save method
    @Override
    @Transactional  // Because we are creating a new entry (modifying database)
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }


    // READ:

    @Override
    public Student findById(Integer theId) {
        return entityManager.find(Student.class, theId);
    }

    @Override
    public List<Student> findAll() {
        // Create query
        // Student comes from the java entity (name of the class) not the database table name
        TypedQuery<Student> query = entityManager.createQuery("FROM Student s ORDER BY s.lastName", Student.class);  // Uses HQL (Hibernate Query language)

        // Return results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        // Create query
        TypedQuery<Student> query = entityManager.createQuery("FROM Student s WHERE s.lastName = :lastName", Student.class);
        query.setParameter("lastName", lastName);

        // Return results
        return query.getResultList();
    }


    // UPDATE:

    @Override
    @Transactional // Since we are performing an update (modifying database)
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }


    // DELETE:

    @Override
    @Transactional  // Since we are deleting (modifying the database)
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class, id);
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}





