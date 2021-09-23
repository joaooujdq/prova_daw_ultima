package com.example.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.services.GestaoLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.dtos.LoginDTO;
import com.example.models.Login;
import com.example.repositories.LoginDAO;
import com.example.services.GestaoLogin;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/autenticacao/login")
@Tag(name = "Endpoint de Login") 
public class LoginController {
	
	
	
	@Autowired
	private GestaoLogin service;
	@Autowired
	private LoginDAO loginDAO;
	
	@GetMapping
	@Operation(summary = "Busca todos os logins")
	public ResponseEntity<CollectionModel<LoginDTO>> buscarTodos(
				@RequestParam(value="page", defaultValue = "0") int page,
				@RequestParam(value="limit", defaultValue = "12") int limit,
				@RequestParam(value="direction", defaultValue = "asc") String direction) {


			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "codigo"));
			
			Page<LoginDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(LoginController.class).buscarUm(p.getCodigo())).withSelfRel()
					)
				);
		  	
			return ResponseEntity.ok(CollectionModel.of(pages));
		}
	
	@GetMapping("/{id}")
	@Operation(summary = "Busca por id")
	public ResponseEntity<LoginDTO> buscarUm(@PathVariable Integer id) {
		LoginDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(LoginController.class).buscarUm(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}
	
	@PostMapping
	@Operation(summary = "Incluir")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LoginDTO> incluir(@RequestBody @Valid Login objBody) {
		LoginDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(LoginController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
}