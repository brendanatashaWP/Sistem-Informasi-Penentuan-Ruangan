package com.piterpan.sipr;

import com.piterpan.sipr.Model.User;
import com.piterpan.sipr.RestCont.UserRestCont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public class SiprApplication implements CommandLineRunner{

	private final static Logger logger = LoggerFactory.getLogger(SiprApplication.class);
	@Autowired
	private UserRestCont userRestCont;

	public static void main(String[] args) {
		SpringApplication.run(SiprApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		setAdmin();
	}

	private void setAdmin() {
		User user = new User();
		try {
			user.setUsername("admin");
			user.setPassword("admin");
			user.setStatusUser("Active");
			user.setNamaUser("Admin");
			user.setRole("Admin");

			userRestCont.addUser(user);
			System.out.println("Create admin success");
		} catch (Exception e) {
			logger.error("Admin was created");
		}
	}
}
