package com.example.lastbuildweek.controllers;

import com.example.lastbuildweek.entities.Comune;
import com.example.lastbuildweek.entities.Provincia;
import com.example.lastbuildweek.services.ProvinciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/provincie")
@Slf4j
@CrossOrigin(origins = "*")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @PostMapping("/add-provincia")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProvincie() throws IOException {
        return provinciaService.addProvincia();
    }

    // RITORNA UN SINGOLO Comun PER ID(PK)
    @GetMapping("/{sigla}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Provincia> get( @PathVariable("sigla") String sigla ) throws Exception {

        return new ResponseEntity<>(
                provinciaService.getBySigla( sigla ),
                HttpStatus.OK
        );
    }


    // AGGIUNGI UN NUOVO COMUNE CON IL BODY COME RICHIESTA
    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public Provincia create( @RequestBody Provincia provincia ) {

        try {

            provinciaService.save( provincia );

            return provincia;

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

        return null;

    }




    //AGGIORNA LE PROPRIETA' DI UN COMUNE
    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void update( @RequestBody Provincia provincia ) {

        try {

            provinciaService.save( provincia );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }
    }


    @DeleteMapping("/delete/{sigla}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById( @PathVariable("sigla") String sigla ) {

        try {

            provinciaService.delete( sigla );

        } catch( Exception e ) {

            log.error( e.getMessage() );

        }

    }
}
