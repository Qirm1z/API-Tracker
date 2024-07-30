package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Attivita;
import com.example.demo.repository.AttivitaRepository;

@Service
public class AttivitaService {

    @Autowired
    private AttivitaRepository attivitaRepository;

    public List<Attivita> getAllDestinazioni() {
        return attivitaRepository.findAll();
    }

    public Optional<Attivita> getAttivitaById(Long id) {
        return attivitaRepository.findById(id);
    }

    public Attivita createAttivita(Attivita attivita) {
        return attivitaRepository.save(attivita);
    }

    public Optional<Attivita> updateAttivita(Long id, Attivita attivita) {
        if (attivitaRepository.existsById(id)) {
            attivita.setId(id);
            return Optional.of(attivitaRepository.save(attivita));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteAttivita(Long id) {
        if (attivitaRepository.existsById(id)) {
            attivitaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
