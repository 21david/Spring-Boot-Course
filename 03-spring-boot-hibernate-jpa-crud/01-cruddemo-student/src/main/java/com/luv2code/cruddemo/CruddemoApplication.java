package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		System.out.println("CruddemoApplication.main");
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	// This will execute on startup after spring beans are loaded
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {  // Spring automatically injects the DAO
		System.out.println("CruddemoApplication.commandLineRunner");
		return runner -> {
			System.out.println("Hello World!");
//			createStudent(studentDAO);

//			createMultipleStudents(studentDAO);

//			readStudent(studentDAO);

//			queryForStudents(studentDAO);

			queryByLastName(studentDAO);


		};
	}

	private void queryByLastName(StudentDAO studentDAO) {
		System.out.println("CruddemoApplication.queryByLastName");

		// Get a list of students with last name Duck
		List<Student> ducks =  studentDAO.findByLastName("Duck");

		Collections.sort(ducks, new Comparator<Student>() {
			// Sort ascending by first name
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			}
		});

		// Display the list of students
		for(Student tempStudent : ducks) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		System.out.println("CruddemoApplication.queryForStudents");

		// Get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// Display the list of students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("CruddemoApplication.readStudent");

		Student student1 = new Student("Dwight", "Schrute", "beats@schrutefarms.com");

		studentDAO.save(student1);

		int theId = student1.getId();
		System.out.println("Saved a new student with id " + theId);

		Student theSameStudent = studentDAO.findById(theId);

		System.out.println("Retrieved that student:" + theSameStudent);

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating and persisting 3 new students...");

		Student student1 = new Student("Mickey", "Mouse", "mickey@disney.com");
		Student student2 = new Student("Donald", "Duck", "donald@disney.com");
		Student student3 = new Student("Steve", "Carell", "michael@theoffice.com");

		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("CruddemoApplication.createStudent");
		
		// Create the student object
		System.out.println("Creating a new student object...");
		Student newStudent = new Student("Dwayne", "Johnson", "therock@sevenbucks.com");
		
		// Save the student object
		System.out.println("Saving student...");
		studentDAO.save(newStudent);
		
		// Display the ID of the saved student
		System.out.println("Saved student. Generated id: " + newStudent.getId());
	}
}
