package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AuthRequest;
import com.example.demo.AuthResponse;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.Token;
import com.example.demo.model.Utente;
import com.example.demo.service.TokenService;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.service.UtenteService;

import jakarta.validation.Valid;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/utenti")
public class UtenteController {

	@Autowired
	private UtenteRepository utenteRepository;
	
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private TokenService tokenService;

	@GetMapping
	public List<Utente> getAllUtenti() {
		return utenteRepository.findAll();
	}

	// Endpoint per ottenere i dati dell'utente loggato
	@GetMapping("/me")
	public Utente getUserData(@RequestHeader("Authorization") String token) {
		System.out.println("token:" + token);
		// Trova il token nel database
		Token authToken = tokenService.findByToken(token);
		
		// Se il token è valido, restituisce i dati dell'utente
		if (authToken != null) {
			return utenteService.findById(authToken.getUserId()).orElseThrow(() -> new UnauthorizedException());
		} else {
			// Se il token non è valido, lancia un'eccezione di non autorizzato
			throw new UnauthorizedException();
		}
	}
	
    // Login endpoint
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        Utente utente = utenteService.findByUsername(authRequest.getUsername());
        if (utente != null && utente.getPassword().equals(authRequest.getPassword())) {
            String token = tokenService.createToken(utente.getId()).getToken();
            return new AuthResponse(token);
        } else {
            throw new UnauthorizedException();
        }
    }
    
	// Endpoint per la registrazione Utente
    @PostMapping("/register")
    public ResponseEntity<Utente> register(@RequestBody Utente utente) {
        utenteService.save(utente);
        return new ResponseEntity<>(utente, HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        Optional<Utente> utente = utenteRepository.findById(id);
        if (utente.isPresent()) {
            return new ResponseEntity<>(utente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	

	@PostMapping
    public ResponseEntity<Utente> createUtente(@Valid @RequestBody Utente utente) {
        Utente savedUtente = utenteRepository.save(utente);
        return new ResponseEntity<>(savedUtente, HttpStatus.CREATED);
    }

	
	@PutMapping("/{id}")
    public ResponseEntity<Utente> updateUtente(@PathVariable Long id, @Valid @RequestBody Utente utente) {
        Optional<Utente> existingUtente = utenteRepository.findById(id);
        if (existingUtente.isPresent()) {
            utente.setId(id);
            Utente updatedUtente = utenteRepository.save(utente);
            return new ResponseEntity<>(updatedUtente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) {
        if (utenteRepository.existsById(id)) {
            utenteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}