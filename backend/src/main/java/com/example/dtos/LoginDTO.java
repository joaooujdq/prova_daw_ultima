package com.example.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.RepresentationModel;

import com.example.models.Login;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonPropertyOrder({"codigo","nome","email", "admin"})
public class LoginDTO extends RepresentationModel<LoginDTO>  {



	@JsonProperty("codigo")
	private Integer codigo ;
	
	@NotBlank
	@Size(max=50)
	@JsonProperty("nome")
	private String nome;
	
	@NotBlank
	@Size(max=50)
	@JsonProperty("email")
	private String email;
	
	@NotBlank
	@JsonProperty("admin")
	private boolean admin;
	
	public LoginDTO () {
	
	}
	
	
	public LoginDTO (Login obj) {
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.admin = obj.isAdmin();
		
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
}