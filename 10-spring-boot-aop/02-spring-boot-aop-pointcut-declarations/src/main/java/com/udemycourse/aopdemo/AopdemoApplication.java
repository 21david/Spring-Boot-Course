package com.udemycourse.aopdemo;

import com.udemycourse.aopdemo.dao.AccountDAO;
import com.udemycourse.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDao, MembershipDAO membershipDAO) {

		return runner -> {
			demoTheBeforeAdvice(accountDao, membershipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		// Since we used the pointcut "execution(public void addAccount())", both of these will cause the advice method to hit
		accountDAO.addAccount();
		membershipDAO.addAccount();
	}
}
