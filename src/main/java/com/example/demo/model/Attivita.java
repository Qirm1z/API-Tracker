package com.example.demo.model;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Attivita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Il tipo di attività è mandatorio")
	private String tipo;
	
	@Range(min=10, max=180, message="La durata dell'attività deve rientrare tra i 10 minuti e le 3 ore, indicare in minuti il tempo della sessione d'allenamento")
	private Integer durata;
	

	@Range(min=1, max=600, message="La distanza percorsa deve rientrare tra l'1 e i 600km")
	private Integer distanza;
	
	@NotNull(message="Il numero di calorie bruciate non può esser nullo")
	private Integer calorie;
	
    private Date sportDate;
	
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;
    
    @Column(nullable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
    
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Date getSportDate() {
		return sportDate;
	}

	public void setSportDate(Date sportDate) {
		this.sportDate = sportDate;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getDurata() {
		return durata;
	}
	
	public void setDurata(Integer durata) {
		this.durata = durata;
	}
	
	public Integer getDistanza() {
		return distanza;
	}
	
	public void setDistanza(Integer distanza) {
		this.distanza = distanza;
	}
	
	public Integer getCalorie() {
		return calorie;
	}
	
	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}
	
	public Utente getUtente() {
		return utente;
	}
	
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}