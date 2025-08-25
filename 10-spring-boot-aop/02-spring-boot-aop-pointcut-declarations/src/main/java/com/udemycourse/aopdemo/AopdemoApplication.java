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
		// Since we used the pointcut "execution(* com.udemycourse.aopdemo.dao.*.*(..))", each of these will cause
		// both the beforeAddAccountAdvice() AND the performApiAnalytics() methods to hit
		Account acc = new Account();
		accountDAO.addAccount(acc, true);
		accountDAO.doWork();

		membershipDAO.addAccount();
		membershipDAO.addDummy();
		membershipDAO.goToSleep();
	}
}
