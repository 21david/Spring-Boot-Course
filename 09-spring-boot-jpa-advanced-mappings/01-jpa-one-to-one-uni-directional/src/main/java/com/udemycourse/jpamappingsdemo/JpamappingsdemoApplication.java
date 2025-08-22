package com.udemycourse.jpamappingsdemo;

import com.udemycourse.jpamappingsdemo.dao.AppDAO;
import com.udemycourse.jpamappingsdemo.entity.Instructor;
import com.udemycourse.jpamappingsdemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpamappingsdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpamappingsdemoApplication.class, args);
//		System.out.println("http://localhost:8080");
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {  // appDAO injected automatically because this is a bean

		return runner -> {
//			createInstructor(appDAO);

//			findInstructor(appDAO);

			deleteInstructor(appDAO);
		};
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
