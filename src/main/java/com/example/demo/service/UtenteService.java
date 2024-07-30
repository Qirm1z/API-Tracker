package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Utente;
import com.example.demo.repository.UtenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    // Trova un utente in base all'username
    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    // Restituisce la lista di tutti gli utenti
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

	public Optional<Utente> findById(Long utenteId) {
		 return utenteRepository.findById(utenteId);
	}

    public void save(Utente utente) {
        // Set default role if not set
        if (utente.getRuolo() == null || utente.getRuolo().isEmpty()) {
            utente.setRuolo("USER");
        }
        utenteRepository.save(utente);
    }
}
