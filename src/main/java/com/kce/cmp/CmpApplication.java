package com.kce.cmp;

import com.kce.cmp.model.user.Role;
import com.kce.cmp.model.user.User;
import com.kce.cmp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class CmpApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CmpApplication.class, args);
	}


	@Override
	public void run(String ...args){
		User adminAccount = userRepository.findByRole(Role.ADMIN).orElse(null);

		if(adminAccount == null) {
			User user = new User();
			user.setName("Admin");
			user.setEmail("admin@gmail.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
			userRepository.save(user);
		}
	}

}
