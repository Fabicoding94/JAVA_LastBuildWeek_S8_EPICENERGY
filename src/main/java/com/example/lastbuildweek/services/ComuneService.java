package com.example.lastbuildweek.services;

import com.example.lastbuildweek.entities.Comune;
import com.example.lastbuildweek.entities.Comune;
import com.example.lastbuildweek.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ComuneService {


    @Autowired
    ComuneRepository comuneRepository;

    public void save(Comune comune){
        comuneRepository.save(comune);
    };

    public Comune getById(Long id) throws Exception {
        Optional<Comune> comune = comuneRepository.findById(id);
        if ( comune.isEmpty() )
            throw new Exception("Cliente not available");
        return comune.get();
    }

    public void delete(Long id) throws Exception {
        Optional<Comune> comune = comuneRepository.findById(id);
        if (comune.isPresent()) {
            comuneRepository.delete(comune.get());
        } else {
            throw new Exception("Cliente non trovato");
        }
    }

    public void update(Comune comune) {
        comuneRepository.save(comune);
    }

    public Page<Comune> getAllPaginate(Pageable p) {
        return comuneRepository.findAll(p);
    }



}
