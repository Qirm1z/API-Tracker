package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Il nome è mandatorio")
	private String nome;
	
	@NotBlank(message ="Il cognome è mandatorio")
	private String cognome;
	
	@NotBlank(message = "L'username è mandatorio")
	private String username;
	
	@NotBlank(message= "La mail è mandatoria")
	@Email(message="L'email deve essere dotata di un formato valido")
	private String email;
	
	@NotBlank(message = "La password è mandatoria")
	private String password;
	
	@NotNull(message= "L'età è mandatoria")
	private Integer eta;

	@Range(min=30, max=150, message= "Il peso dev'essere compreso tra 30kg e 150kg")
	private String peso;
	
	@Range(min=120, max=210, message= "L'altezza dev'essere compreso tra 120cm e 210cm")
	private String altezza;
	
	@Nullable
	private Integer mMI;
	
	@Column(nullable=false)
	private String ruolo = "USER"; //Ruolo default
	
	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public Integer getmMI() {
		return mMI;
	}

	public void setmMI(Integer mMI) {
		this.mMI = mMI;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getAltezza() {
		return altezza;
	}

	public void setAltezza(String altezza) {
		this.altezza = altezza;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
}
