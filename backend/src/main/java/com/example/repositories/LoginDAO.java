package com.example.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Login;



public interface LoginDAO extends JpaRepository<Login, Integer>{
	
		
		 public Optional<Login> findByNome (String nome);
		 public Optional<Login> findByEmail(String email);



}
