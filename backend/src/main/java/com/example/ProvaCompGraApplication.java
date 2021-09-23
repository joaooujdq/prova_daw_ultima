package com.example;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.models.Login;
import com.example.repositories.LoginDAO;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class ProvaCompGraApplication implements CommandLineRunner{
	
	@Autowired
	private LoginDAO loginDAO;

	public static void main(String[] args) {
		SpringApplication.run(ProvaCompGraApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Login l1 = new Login(1, "Joao", "email1", true);
		Login l2 = new Login(2, "marcelo", "email2", false );
	
		loginDAO.saveAll(Arrays.asList(l1,l2));
	}
}
