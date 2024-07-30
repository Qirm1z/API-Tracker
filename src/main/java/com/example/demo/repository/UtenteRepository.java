package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
}
