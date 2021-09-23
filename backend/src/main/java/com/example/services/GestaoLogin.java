package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dtos.LoginDTO;
import com.example.models.Login;
import com.example.repositories.LoginDAO;
import com.example.services.exception.BusinessException;

@Service
public class GestaoLogin {

@Autowired
private LoginDAO loginDAO;

@Transactional(readOnly = true)
public Page<LoginDTO> findAll(Pageable pageable) {	
	
	Page<Login> result = loginDAO.findAll(pageable);
	 
	return result.map(obj -> new LoginDTO(obj));
	
	}	

@Transactional(readOnly = true)
public LoginDTO findById(Integer id) {
	Login result = loginDAO.findById(id).
			orElseThrow(() -> new BusinessException("Registros não encontrados!!!"));
	
	return new LoginDTO(result);
		
}
	@Transactional
	public LoginDTO save(Login obj) {	
		
		boolean emailExists = loginDAO.findByEmail(obj.getEmail())
				.stream()
				.anyMatch(objResult -> !objResult.equals(obj));
		
		if(emailExists) {
			throw new BusinessException("Email repetido!");
		}
		
		return new LoginDTO(loginDAO.save(obj));
	}
	

	public boolean existById(Integer id) {
		return loginDAO.existsById(id);
	}
}
