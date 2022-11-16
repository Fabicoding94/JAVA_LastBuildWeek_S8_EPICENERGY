package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.IndirizzoLegale;
import com.example.lastbuildweek.entities.IndirizzoOperativo;
import com.example.lastbuildweek.entities.Provincia;
import com.example.lastbuildweek.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProvinciaService {

    @Autowired
    ProvinciaRepository provinciaRepository;

    public void save(Provincia p){
        provinciaRepository.save(p);
    };

    public Provincia getById(Long id) throws Exception {
        Optional< Provincia> provincia = provinciaRepository.findById(id);
        if ( provincia.isEmpty() )
            throw new Exception("Provincia not available");
        return provincia.get();
    }

    public void delete(Long id) throws Exception {
        Optional< Provincia> provincia = provinciaRepository.findById(id);
        if (provincia.isPresent()) {
            provinciaRepository.delete(provincia.get());
        } else {
            throw new Exception("Provincia non trovato");
        }
    }

    public void update( Provincia provincia) {
        provinciaRepository.save(provincia);
    }

    public Page<Provincia> getAllPaginate(Pageable p) {
        return provinciaRepository.findAll(p);
    }
}
