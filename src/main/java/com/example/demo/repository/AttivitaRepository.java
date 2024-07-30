package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Attivita;

public interface AttivitaRepository  extends JpaRepository<Attivita, Long> {

}
