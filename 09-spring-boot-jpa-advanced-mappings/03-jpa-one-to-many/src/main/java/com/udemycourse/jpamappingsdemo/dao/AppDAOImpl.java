package com.udemycourse.jpamappingsdemo.dao;

import com.udemycourse.jpamappingsdemo.entity.Instructor;
import com.udemycourse.jpamappingsdemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

        // Because of CascadeType.ALL in Instructor, this also deletes any associated InstructorDetail in the database
        entityManager.remove(toDelete);
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


}

