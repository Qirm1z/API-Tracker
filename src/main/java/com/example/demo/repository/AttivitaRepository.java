package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Attivita;

public interface AttivitaRepository  extends JpaRepository<Attivita, Long> {

    List<Attivita> findByUtenteId(Long utenteId);

    List<Attivita> findByCreatedAtAfter(LocalDate date);
}
