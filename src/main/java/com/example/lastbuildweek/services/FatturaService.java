package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.Cliente;
import com.example.lastbuildweek.entities.Comune;
import com.example.lastbuildweek.entities.Fattura;
import com.example.lastbuildweek.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FatturaService {
    @Autowired
    FatturaRepository fatturaRepository;

    public void save(Fattura comune){
        fatturaRepository.save(comune);
    };

    public Fattura getById(Long id) throws Exception {
        Optional<Fattura> fattura = fatturaRepository.findById(id);
        if ( fattura.isEmpty() )
            throw new Exception("Fattura not available");
        return fattura.get();
    }

    public void delete(Long id) throws Exception {
        Optional<Fattura> fatture = fatturaRepository.findById(id);
        if (fatture.isPresent()) {
            fatturaRepository.delete(fatture.get());
        } else {
            throw new Exception("Fattura non trovato");
        }
    }

    public void update(Fattura fattura) {
        fatturaRepository.save(fattura);
    }

    public Page<Fattura> getAllPaginate(Pageable p) {
        return fatturaRepository.findAll(p);
    }
}
