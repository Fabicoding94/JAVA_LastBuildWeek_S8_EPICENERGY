package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.Cliente;
import com.example.lastbuildweek.entities.IndirizzoLegale;
import com.example.lastbuildweek.repositories.ClienteRepository;
import com.example.lastbuildweek.repositories.IndirizzoLegaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IndirizzoLegaleService {

    @Autowired
    IndirizzoLegaleRepository indirizzoLegaleRepository;

    public void save(IndirizzoLegale indirizzoLegale){
        indirizzoLegaleRepository.save(indirizzoLegale);
    };

    public IndirizzoLegale getById(Long id) throws Exception {
        Optional<IndirizzoLegale> indirizzoLegale = indirizzoLegaleRepository.findById(id);
        if ( indirizzoLegale.isEmpty() )
            throw new Exception("IndirizzoLegale not available");
        return indirizzoLegale.get();
    }

    public void delete(Long id) throws Exception {
        Optional<IndirizzoLegale> indirizzoLegale = indirizzoLegaleRepository.findById(id);
        if (indirizzoLegale.isPresent()) {
            indirizzoLegaleRepository.delete(indirizzoLegale.get());
        } else {
            throw new Exception("Indirizzo Legale non trovato");
        }
    }

    public void update(IndirizzoLegale indirizzoLegale) {
        indirizzoLegaleRepository.save(indirizzoLegale);
    }

    public Page<IndirizzoLegale> getAllPaginate(Pageable p) {
        return indirizzoLegaleRepository.findAll(p);
    }
}
