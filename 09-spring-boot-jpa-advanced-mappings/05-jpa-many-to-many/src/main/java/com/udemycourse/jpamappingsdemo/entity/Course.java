package com.udemycourse.jpamappingsdemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="course")
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    // Many courses can map to one instructor. We make sure deletions don't cascade.
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)  //CascadeType.ALL means anything done to this course will also happen to the reviews
    @JoinColumn(name="course_id")  // the 'course_id' column in reviews is the one that points to this course
    private List<Review> reviews;

    // MANY TO MANY
    // Course is the owning side in thie case
    @ManyToMany(fetch=FetchType.LAZY,
                cascade={CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",  // the "bridge" table (join table) that has pairs of (course_id, student_id), which are basically edges in a bipartite graph
            joinColumns = @JoinColumn(name = "course_id"),  // column in the join table that points to this course
            inverseJoinColumns = @JoinColumn(name = "student_id")  // column in the join table that points to the student that takes this course
    )
    private List<Student> students;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void add(Review review) {
        if (reviews == null)
            reviews = new ArrayList<>();

        reviews.add(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // convenience method for students
    public void addStudent(Student student) {
        if (students == null)
            students = new ArrayList<>();

        students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
