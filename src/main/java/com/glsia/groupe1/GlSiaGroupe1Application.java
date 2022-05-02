package com.glsia.groupe1;

import com.glsia.groupe1.models.Role;
import com.glsia.groupe1.models.User;
import com.glsia.groupe1.service.RoleService;
import com.glsia.groupe1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class GlSiaGroupe1Application {

	public static void main(String[] args) {
		SpringApplication.run(GlSiaGroupe1Application.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}




/*
	@Bean
	CommandLineRunner run(RoleService roleService, UserService userService){
		return args -> {

			roleService.save(new Role(12,"ROLE_USER"));
			roleService.save(new Role(13,"ROLE_ADMIN"));

			userService.save(new User(13,"John Doe","John","123456789",new ArrayList<>()));
			userService.save(new User(24,"Will Smith","Will","123456789",new ArrayList<>()));
			userService.save(new User(31,"Samson Bada","Translucide","123456789",new ArrayList<>()));

			userService.addRoleToUser("John","ROLE_USER");
			userService.addRoleToUser("Will","ROLE_ADMIN");
			userService.addRoleToUser("Translucide","ROLE_ADMIN");
			userService.addRoleToUser("Translucide","ROLE_USER");

		};
	}
*/
}
