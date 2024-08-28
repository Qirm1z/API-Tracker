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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Attivita;
import com.example.demo.service.AttivitaService;

import jakarta.validation.Valid;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/act")
public class AttivitaController {

	@Autowired
	private AttivitaService attivitaService;
	
    // Get all destinations
    @GetMapping
    public List<Attivita> getAllDestinazioni() {
        return attivitaService.getAllDestinazioni();
    }

    // Get a destination by ID
    @GetMapping("/{id}")
    public ResponseEntity<Attivita> getAttivitaById(@PathVariable Long id) {
        Optional<Attivita> attivita = attivitaService.getAttivitaById(id);
        return attivita.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Get all destinations by ID
    @GetMapping("/utente/{utenteId}")
    public List<Attivita> getAttivitaByUtenteId(@PathVariable Long utenteId) {
        return attivitaService.getAttivitaByUserId(utenteId);
    }
    
    // Get all destinations for the week
    @GetMapping("/week")
    public List<Attivita> getAttivitaWeekly() {
        return attivitaService.getAttivitaWeekly();
    }

    // Create a new destination
    @PostMapping
    public ResponseEntity<Attivita> createAttivita(@Valid @RequestBody Attivita attivita) {
        Attivita savedAttivita = attivitaService.createAttivita(attivita);
        return new ResponseEntity<>(savedAttivita, HttpStatus.CREATED);
    }

    // Update a destination by ID
    @PutMapping("/{id}")
    public ResponseEntity<Attivita> updateAttivita(@PathVariable Long id, @Valid @RequestBody Attivita attivita) {
        Optional<Attivita> updatedAttivita = attivitaService.updateAttivita(id, attivita);
        return updatedAttivita.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                                  .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a destination by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttivita(@PathVariable Long id) {
        boolean isDeleted = attivitaService.deleteAttivita(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
