package com.udemycourse.jpamappingsdemo;

import com.udemycourse.jpamappingsdemo.dao.AppDAO;
import com.udemycourse.jpamappingsdemo.entity.Course;
import com.udemycourse.jpamappingsdemo.entity.Instructor;
import com.udemycourse.jpamappingsdemo.entity.InstructorDetail;
import com.udemycourse.jpamappingsdemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpamappingsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpamappingsdemoApplication.class, args);
//		System.out.println("http://localhost:8080");
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {  // appDAO injected automatically because this is a bean

		return runner -> {

//			createCoursesAndReviews(appDAO);

//			retrieveCourseAndReviews(appDAO);

			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteCourseAndReviews(AppDAO dao) {
		int id = 10;

		// This should also delete all associated reviews because CascadeType.ALL is set on Course's "reviews" List property
		dao.deleteCourseById(id);
	}

	private void retrieveCourseAndReviews(AppDAO dao) {
		int id = 10;

		Course course = dao.findCourseAndReviewsByCourseId(id);

		System.out.println("Retrieved course: " + course);
		System.out.println("Retrieved course reviews: " + course.getReviews());
	}

	private void createCoursesAndReviews(AppDAO dao) {

		Course course = new Course("Pacman - How to score 1M points");

		course.add(new Review("Awesome course!"));
		course.add(new Review("Great course! well done!!"));
		course.add(new Review("Dumb course dont take it"));

		// save the course, and leverage the cascade all to also save the reviews in the db
		dao.save(course);

		System.out.println("Course: " + course + " | REVIEWS: " + course.getReviews());

	}

	private void deleteCourse(AppDAO dao) {
		int id = 11;
		dao.deleteCourseById(id);
	}

	private void updateCourse(AppDAO dao) {
		int id = 10;

		Course cs = dao.findCourseById(id);

		cs.setTitle("US History");

		dao.update(cs);
	}

	private void updateInstructor(AppDAO dao) {

		int id = 1;

		// get instructor object
		Instructor ins = dao.findInstructorById(id);

		// update
		ins.setLastName("NewLast");

		// update in db
		dao.update(ins);

	}

	private void findInstructorWithCoursesJoinFetch(AppDAO dao) {
		int id = 1;

		Instructor instructor = dao.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);
		System.out.println("instructor courses: " + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO dao) {
		Instructor instructor = dao.findInstructorById(1);

		System.out.println("Instructor: " + instructor);

		// courses are lazy loaded, so we must manually get them
		List<Course> courses = dao.findCoursesByInstructorId(1);

		instructor.setCourses(courses);

		// This throws LazyInitializationException if courses are not explicitly set to eager loading (bc OneToMany is lazy loading by default) ?
		System.out.println("Instructor Courses: " + instructor.getCourses());

	}

	private void findInstructorWithCourses(AppDAO dao) {

		Instructor instructor = dao.findInstructorById(1);

		System.out.println("Instructor: " + instructor);
		// This throws LazyInitializationException if courses are not explicitly set to eager loading (bc OneToMany is lazy loading by default)
		System.out.println("Instructor courses: " + instructor.getCourses());


	}

	private void createInstructorWithCourses(AppDAO dao) {

		Instructor ins = new Instructor("Sal", "Khan", "sal@gmail.com");

		InstructorDetail insDt = new InstructorDetail("youtube.com/sal", "coding");

		ins.setInstructorDetail(insDt);

		Course c1 = new Course("Calculus I");
		Course c2 = new Course("Calculus II");
		Course c3 = new Course("Chemistry");

		ins.add(c1);
		ins.add(c2);
		ins.add(c3);

		System.out.println("Saving instructor " + ins);
		System.out.println("His courses" + ins.getCourses());

		// This will also save the courses to the db because of CascadeType.PERSIST
		dao.save(ins);
	}

	private void deleteInstructorDetail(AppDAO dao) {
		dao.deleteInstructorDetailById(3); // this deletes the instructor bc cascading is set
	}

	private void findInstructorDetail(AppDAO dao) {

		InstructorDetail insD = dao.findInstructorDetailById(2);

		System.out.println("insD: " + insD);
		System.out.println("the associated instructor: " + insD.getInstructor());  // We have access to this bc of cascading
	}

	private void deleteInstructor(AppDAO dao) {
		dao.deleteInstructorById(1);
	}

	private void findInstructor(AppDAO appDAO) {

		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor tempIns = appDAO.findInstructorById(id);

		System.out.println("tempInstructor: " + tempIns);
		System.out.println("Associated instructorDetail: " + tempIns.getInstructorDetail());

	}

	public void createInstructor(AppDAO appDAO) {

		Instructor ins = new Instructor("David", "Esp", "david@gmail.com");

		InstructorDetail insDt = new InstructorDetail("youtube.com/david", "coding");

		ins.setInstructorDetail(insDt);

		System.out.println("Saving instructor: " + ins);
		appDAO.save(ins); // Bc of cascading (CascadeType.ALL), this also saves the details object!
	}

}
