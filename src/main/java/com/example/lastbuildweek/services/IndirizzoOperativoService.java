package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.IndirizzoLegale;
import com.example.lastbuildweek.entities.IndirizzoOperativo;
import com.example.lastbuildweek.repositories.IndirizzoOperativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndirizzoOperativoService {

    @Autowired
    IndirizzoOperativoRepository indirizzoOperativoRepository;

    public void save(IndirizzoOperativo indirizzoOperativo){
        indirizzoOperativoRepository.save(indirizzoOperativo);
    };

    public IndirizzoOperativo getById(Long id) throws Exception {
        Optional<IndirizzoOperativo> indirizzoOperativo= indirizzoOperativoRepository.findById(id);
        if ( indirizzoOperativo.isEmpty() )
            throw new Exception("Indirizzo Operativo not available");
        return indirizzoOperativo.get();
    }

    public void delete(Long id) throws Exception {
        Optional<IndirizzoOperativo> indirizzoOperativo = indirizzoOperativoRepository.findById(id);
        if (indirizzoOperativo.isPresent()) {
            indirizzoOperativoRepository.delete(indirizzoOperativo.get());
        } else {
            throw new Exception("Indirizzo Operativo non trovato");
        }
    }

    public void update(IndirizzoOperativo indirizzoOperativo) {
        indirizzoOperativoRepository.save(indirizzoOperativo);
    }

    public Page<IndirizzoOperativo> getAllPaginate(Pageable p) {
        return indirizzoOperativoRepository.findAll(p);
    }
}
